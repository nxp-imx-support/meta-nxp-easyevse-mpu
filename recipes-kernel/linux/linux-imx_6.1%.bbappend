FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://0001-EasyEVSE-LF6.1.1-dts-patches-for-i.MX8MNLPDDR4-and-i.patch \
    file://0001-PATCH-EasyEVSE-LF6.1.1-pn7160-driver-add-for-i.MX8MN.patch \
"

do_copy_defconfig:append () {
    echo "CONFIG_NXP_NFC_I2C=y" >>  ${B}/.config
    echo "CONFIG_NXP_NFC_SPI=y" >>  ${B}/.config
    echo "CONFIG_NXP_NFC_RECOVERY=y" >>  ${B}/.config
}
