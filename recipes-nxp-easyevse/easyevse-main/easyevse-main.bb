SUMMARY = "NXP EASYEVSE Main"
DESCRIPTION = "Server, Meter, NFC client implementation"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78608ff1e465e39856cca06c2febf664"

inherit autotools pkgconfig

TARGET_CC_ARCH += "${LDFLAGS}"

DEPENDS = "cjson libnfceasyevse"
RDEPENDS:${PN} = "cjson libnfceasyevse"

PV = "1.0+${SRCPV}"

S = "${WORKDIR}/git"

NXP_EASYEVSE_SRC ?= "git://github.com/nxp-imx-support/nxp-easyevse-mpu.git;protocol=https"

SRCBRANCH_server = "lf-6.1.1_1.0.0_0.1.0"

SRC_URI= "\
	${NXP_EASYEVSE_SRC};branch=${SRCBRANCH_server};name=server \
	 "

SRCREV_server = "bc1de3c88479202d917405bca44a8ba69a3d5d6e"

METER_TARGET:imx93-11x11-lpddr4x-evk = "-Dimx93evk"
METER_TARGET:imx8mn-lpddr4-evk = "-Dimx8mnevk"

export METER_TARGET
export WORKDIR


do_compile() {
	cd ${WORKDIR}/git
	oe_runmake all
}

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-easyevse
    cp -r ${WORKDIR}/git/release/* ${D}/home/root/.nxp-easyevse
}

FILES:${PN} += "/home/root/.nxp-easyevse/* "
