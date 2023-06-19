SUMMARY = "NXP EASYEVSE CLOUD"
DESCRIPTION = "CLOUD client implementation"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78608ff1e465e39856cca06c2febf664"

inherit cmake

DEPENDS += " cjson curl openssl util-linux-libuuid"

TARGET_CC_ARCH += "${LDFLAGS}"

PV = "1.0+${SRCPV}"

S = "${WORKDIR}/git"

NXP_EASYEVSE_SRC ?= "git://github.com/nxp-imx-support/nxp-easyevse-mpu.git;protocol=https"
NXP_AZURE_SRC ?= "gitsm://github.com/Azure/azure-iot-sdk-c.git;destsuffix=git/azure-iot-sdk-c;protocol=https"

SRCBRANCH_cloud = "lf-6.1.1_1.0.0_0.1.0"
SRCBRANCH_azure = "lts_01_2023"
SRCTAG_azure = "LTS_01_2023_Ref01"

SRC_URI= "\
	${NXP_EASYEVSE_SRC};branch=${SRCBRANCH_cloud};name=cloud \
	${NXP_AZURE_SRC};branch=${SRCBRANCH_azure};tag=${SRCTAG_azure};name=azure \
	 "

SRCREV_cloud = "bc1de3c88479202d917405bca44a8ba69a3d5d6e"

SRCREV_FORMAT = "cloud_azure"

EXTRA_OECMAKE += "-DTARGET=CLOUD"

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-easyevse
    install ${WORKDIR}/debug/CLOUD ${D}/home/root/.nxp-easyevse
	install ${S}/cloud/cloud.conf ${D}/home/root/.nxp-easyevse
}

FILES:${PN} += "/home/root/.nxp-easyevse/* "


