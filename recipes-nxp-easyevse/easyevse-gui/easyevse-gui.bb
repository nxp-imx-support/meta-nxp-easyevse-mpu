SUMMARY = "NXP EASYEVSE GUI"
DESCRIPTION = "GUI client implementation"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78608ff1e465e39856cca06c2febf664"

inherit qt6-cmake

DEPENDS += " cjson qtconnectivity qtsvg qtquick3d-native packagegroup-qt6-imx"
RDEPENDS:${PN} += " cjson weston bash qtsvg-plugins qt5compat"

TARGET_CC_ARCH += "${LDFLAGS}"

PV = "1.0+${SRCPV}"

S = "${WORKDIR}/git"

NXP_EASYEVSE_SRC ?= "git://github.com/nxp-imx-support/nxp-easyevse-mpu.git;protocol=https"

SRCBRANCH_gui = "lf-6.1.1_1.0.0_0.1.0"

SRC_URI= "\
	${NXP_EASYEVSE_SRC};branch=${SRCBRANCH_gui};name=gui \
	 "

SRCREV_gui = "bc1de3c88479202d917405bca44a8ba69a3d5d6e"

EXTRA_OECMAKE += "-DTARGET=GUI"


do_install() {
    install -d -m 755 ${D}/home/root/.nxp-easyevse
    install ${WORKDIR}/build/gui/GUI ${D}/home/root/.nxp-easyevse
}

FILES:${PN} += "/home/root/.nxp-easyevse/* "


