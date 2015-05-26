function _change() {
	$("#vCode").attr("src", "/geekbook/VerifyCodeServlet?" + new Date().getTime());
}