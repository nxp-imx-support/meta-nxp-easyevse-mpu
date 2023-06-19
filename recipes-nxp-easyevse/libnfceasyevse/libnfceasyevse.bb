SUMMARY = "NXP LibNFC"
DESCRIPTION = "NXP NFC Stack and demo application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=86d3f3a95c324c9479bd8986968f4327"

inherit autotools pkgconfig

TARGET_CC_ARCH += "${LDFLAGS}"

PV = "1.0+${SRCPV}"

S = "${WORKDIR}/git"

NXP_EASYEVSE_SRC ?= "git://github.com/NXPNFCLinux/linux_libnfc-nci.git;protocol=https"
SRCBRANCH_nfc = "NCI2.0_PN7160"

SRC_URI= "\
    ${NXP_EASYEVSE_SRC};branch=${SRCBRANCH_nfc};name=nfc \
	file://0001-LIBNFC-NCI-Added-support-for-i.MX-Linux-BSP-build.patch"

SRCREV_nfc = "6bf9f42b94e267f6384043009bda84c11e7ebbaa"

do_compile:append() {
	cd ${S}/demoapp
	${CC} -c tools.c
	${AR} crs libtoolnfc.a tools.o
}

do_install:append() {
	install -c -m 644 ${S}/demoapp/libtoolnfc.a ${D}${libdir}
	mkdir -p ${D}/usr/local/etc
	install -c -m 644 ${D}/etc/* ${D}/usr/local/etc
	install -c -m 644 ${S}/demoapp/tools.h ${D}/usr/include
}


FILES:${PN} += " /usr/* "
