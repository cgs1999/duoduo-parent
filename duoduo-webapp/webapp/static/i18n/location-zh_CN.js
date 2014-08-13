/** 系统菜单 */
$.namespace("Mo.Menu");
Mo.Menu = {
	rootMenuId: -1,
	allMenus: [
		{"id":10,"name":"系统配置","parentId":-1,"url":""},
		{"id":1010,"name":"资源管理","parentId":10,"url":"/system/resource/list"},
		{"id":1020,"name":"角色管理","parentId":10,"url":"/system/role/list"},
		{"id":1030,"name":"用户管理","parentId":10,"url":"/system/user/list"}
	]
};

/** 基础帮助类 */
$.namespace("Mo.i18n");
Mo.i18n = {
	_lang:'zh-CN',
	setLang:function(lang){
		this._lang = lang;
		return this.getLang();
	},
	getLang:function(){
		return Mo.i18n[this._lang];
	}
};

/** 系统所用到的语言包 */
Mo.i18n['zh-CN'] = {
	test:"测试",
	//登录模块start
	login_login_msg_ajax_error:"登录异常，请与系统管理员联系！",
	login_forgot_msg_regEmail_empty:"请输入您的注册邮箱！",
	login_login_msg_account_empty:"请输入用户名！",
	login_login_msg_password_empty:"请输入密码！",
	login_login_msg_verifyCode_empty:"请输入验证码！"
	//登录模块end
};

/** 语法糖方便调用*/
$lang = Mo.i18n.setLang("zh-CN");

/** 第三方对应的语言包*/










