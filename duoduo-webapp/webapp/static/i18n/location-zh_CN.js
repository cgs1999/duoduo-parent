/** 系统菜单 */
$.namespace("Mo.Menu");
Mo.Menu = {
	rootMenuId: -1,
	allMenus: [
		{"id":10,"name":"用户域服务","parentId":-1,"url":""},
		{"id":1010,"name":"用户域信息","parentId":10,"url":"/service/domaininfo"},
		{"id":1020,"name":"组织架构","parentId":10,"url":"/service/user"},
		{"id":1030,"name":"管理员设定","parentId":10,"url":"/service/adminsetting"},
		{"id":1040,"name":"监控设备管理","parentId":10,"url":"/service/monitor"},
		{"id":1050,"name":"共享通讯录","parentId":10,"url":"/service/shareaddress"},
		{"id":20,"name":"统计报表","parentId":-1,"url":""},
		{"id":2010,"name":"点对点记录","parentId":20,"url":"/statistics/p2p"},
		{"id":2020,"name":"多点记录","parentId":20,"url":"/statistics/multiple"},
		{"id":2030,"name":"电话记录","parentId":20,"url":"/statistics/telephone"},
		{"id":2040,"name":"会议统计","parentId":20,"url":"/statistics/total"},
		{"id":30,"name":"订单业务","parentId":-1,"url":""},
		{"id":3010,"name":"订单管理","parentId":30,"url":"/order/list"}
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









