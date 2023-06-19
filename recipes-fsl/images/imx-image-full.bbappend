ROOTFS_POSTPROCESS_COMMAND:append:mx93-nxp-bsp = "install_demo_easyevse; "
ROOTFS_POSTPROCESS_COMMAND:append:mx8-nxp-bsp = "install_demo_easyevse; "

install_demo_easyevse() {

		printf "\n\n[output]\nname=DSI-1\nmode=1920x1080@60\ntransform=rotate-270" >> ${IMAGE_ROOTFS}${sysconfdir}/xdg/weston/weston.ini

}