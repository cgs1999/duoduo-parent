$.namespace("Mo.Dialog");
Mo.Dialog = {
	// 关于对话框
	showAbout : function(){
		$.dialog.open(Mo.Config.appUrl + "/dialog/about", {
			id: "showAboutDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			padding: 0,
			width: 470,
			height: 250,
			close: function () {
				//
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},
	
	// 设置对话框
	showSetting : function(){
		$.dialog.open(Mo.Config.appUrl + "/dialog/userinfo", {
			id: "showSettingDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			padding: 0,
			width: 490,
			height: 407,
			close: function () {
				//
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},
	
	// 修改密码对话框
	showModifyPassword : function(){
		$.dialog.open(Mo.Config.appUrl + "/dialog/modifyPassword", {
			id: "showModifyPasswordDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			padding: 0,
			width: 490,
			height: 394,
			close: function () {
				//
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},
	
	// 退出对话框
	showLogout : function(){
		$.dialog.open(Mo.Config.appUrl + "/dialog/logout", {
			id: "showLogoutDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			padding: 0,
			width: 390,
			height: 260,
			close: function () {
				//
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},
	
	//选择部门对话框，返回多个值，以半角逗号分隔
	selectDepartment : function(deptIdsId, deptNamesId, callback) {
		var objectIds = $('#' + deptIdsId);
		if(!objectIds) {
			alert("部门id接收对象" + deptIdsId + "不存在！");
			return;
		}
		var objectNames = $('#' + deptNamesId);
		if(!objectNames) {
			alert("部门名称接收对象" + deptNamesId + "不存在！");
			return;
		}
		
		$.dialog.data('idKey', "id");
		$.dialog.data('ids', objectIds.val());
		$.dialog.open(Mo.Config.appUrl + "/dialog/selectDepartment", {
			id: "selectDepartmentDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			title: "选择部门",
			padding: 0,
			width: 360,
			height: 440,
			close: function () {
				var deptIds = $.dialog.data('ids');
				if(deptIds!=objectIds.val()) {
					objectIds.val(deptIds);
					objectNames.val($.dialog.data('names'));
				}
				if(typeof(callback)=="function") {
					callback();
				}
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},

	// 选择人员对话框，返回多个值，以半角逗号分隔
	selectUsers : function(userMoidsId, userNamesId, callback){
		var objectIds = $('#' + userMoidsId);
		if(!objectIds) {
			alert("角色id接收对象" + userMoidsId + "不存在！");
			return;
		}
		var objectNames = $('#' + userNamesId);
		if(!objectNames) {
			alert("角色名称接收对象" + userNamesId + "不存在！");
			return;
		}
		
		$.dialog.data('userMoids', objectIds.val());
		$.dialog.open(Mo.Config.appUrl + "/dialog/selectUsers", {
			id: "selectUsersDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			title: "选择人员",
			padding: 0,
			width: 720,
			height: 520,
			close: function () {
				var userMoids = $.dialog.data('userMoids');
				if(userMoids!=objectIds.val()) {
					objectIds.val(userMoids);
					objectNames.val($.dialog.data('userNames'));
				}
				if(typeof(callback)=="function") {
					callback();
				}
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},

	// 选择角色对话框，返回多个值，以半角逗号分隔
	selectRoles : function(roleIdsId, roleNamesId, callback){
		var objectIds = $('#' + roleIdsId);
		if(!objectIds) {
			alert("角色id接收对象" + roleIdsId + "不存在！");
			return;
		}
		var objectNames = $('#' + roleNamesId);
		if(!objectNames) {
			alert("角色名称接收对象" + roleNamesId + "不存在！");
			return;
		}
		
		$.dialog.data('roleIds', objectIds.val());
		$.dialog.open(Mo.Config.appUrl + "/system/role/selectFromAllRole", {
			id: "selectRoleDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			title: "选择角色",
			padding: 0,
			width: 620,
			height: 440,
			close: function () {
				var roleIds = $.dialog.data('roleIds');
				if(roleIds!=objectIds.val()) {
					objectIds.val(roleIds);
					objectNames.val($.dialog.data('roleNames'));
				}
				if(typeof(callback)=="function") {
					callback();
				}
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},

	// 选择菜单对话框，返回多个值，以半角逗号分隔
	selectMenus : function(menuIdsId, menuNamesId, callback) {
		var objectIds = $('#' + menuIdsId);
		if(!objectIds) {
			alert("菜单id接收对象" + menuIdsId + "不存在！");
			return;
		}
		var objectNames = $('#' + menuNamesId);
		if(!objectNames) {
			alert("菜单名称接收对象" + menuNamesId + "不存在！");
			return;
		}
		
		$.dialog.data('menuIds', objectIds.val());
		$.dialog.open(Mo.Config.appUrl + "/system/menu/selectFromAllMenu", {
			id: "selectMenuDialog",
			lock: true,
			opacity: 0.50,	// 透明度
			title: "选择菜单",
			padding: 0,
			width: 360,
			height: 440,
			close: function () {
				var menuIds = $.dialog.data('menuIds');
				if(menuIds!=objectIds.val()) {
					objectIds.val(menuIds);
					objectNames.val($.dialog.data('menuNames'));
				}
				if(typeof(callback)=="function") {
					callback();
				}
			},
			cancel:false, // 隐藏关闭按钮
			drag: false // 不允许拖拽
		}, false);
	},
	
	// 选择服务域对话框，返回一个服务域的moid
	selectServiceDomain : function(serviceDomainMoidId, serviceDomainNameId, callback) {
		var objectId = $('#' + serviceDomainMoidId);
		if(!objectId) {
			alert("服务域moid接收对象" + serviceDomainMoidId + "不存在！");
			return;
		}
		var objectName = $('#' + serviceDomainNameId);
		if(!objectName) {
			alert("服务域名称接收对象" + serviceDomainNameId + "不存在！");
			return;
		}
		
		$.dialog.data('serviceDomainMoid', objectId.val());
		$.dialog.open(Mo.Config.appUrl + "/domain/serviceDomain/selectFromMyService", {
			id : "selectServiceDomainDialog",
			lock : true,
			opacity : 0.50, // 透明度
			title : "选择上级服务域",
			padding : 0,
			width : 360,
			height : 440,
			close : function() {
				var parentId = $.dialog.data('serviceDomainMoid');
				if (parentId != objectId.val()) {
					objectId.val(parentId);
					objectName.val($.dialog.data('serviceDomainName'));
				}
				
				if(typeof callback == "function"){
					callback();
			    }
			},
			cancel : false, // 隐藏关闭按钮
			drag : false
		// 不允许拖拽
		}, false);
	},
	
	// 选择平台域对话框，返回一个平台域的moid
	selectPlatformDomain : function(platformDomainMoidId, platformDomainNameId, callback) {
		var objectId = $('#' + platformDomainMoidId);
		if(!objectId) {
			alert("平台域moid接收对象" + platformDomainMoidId + "不存在！");
			return;
		}
		var objectName = $('#' + platformDomainNameId);
		if(!objectName) {
			alert("平台域名称接收对象" + platformDomainNameId + "不存在！");
			return;
		}
		
		$.dialog.data('platformDomainMoid', objectId.val());
		$.dialog.open(Mo.Config.appUrl + "/domain/platformDomain/selectFromMyPlatform", {
			id : "selectPlatformDomainDialog",
			lock : true,
			opacity : 0.50, // 透明度
			title : "选择上级平台域",
			padding : 0,
			width : 360,
			height : 440,
			close : function() {
				var parentId = $.dialog.data('platformDomainMoid');
				if (parentId != objectId.val()) {
					objectId.val(parentId);
					objectName.val($.dialog.data('platformDomainName'));
				}
				
				if(typeof(callback)=="function") {
					callback();
				}
			},
			cancel : false, // 隐藏关闭按钮
			drag : false
		// 不允许拖拽
		}, false);
	},
	
	// 选择用户域对话框，返回一个平台域的moid
	selectUserDomain : function(userDomainMoidId, userDomainNameId, groupName, serviceDomainMoid, serviceDomainName, callback) {
		var objectId = $('#' + userDomainMoidId);
		if(!objectId) {
			alert("用户域moid接收对象" + userDomainMoidId + "不存在！");
			return;
		}
		var objectName = $('#' + userDomainNameId);
		if(!objectName) {
			alert("用户域名称接收对象" + userDomainNameId + "不存在！");
			return;
		}
		
		var groupName = $('#' + groupName);
		
		$.dialog.data('userDomainMoid', objectId.val());
		$.dialog.data('serviceDomainMoid', serviceDomainMoid);
		$.dialog.data('serviceDomainName', serviceDomainName);
		$.dialog.open(Mo.Config.appUrl + "/domain/userDomain/selectFromMyUserDomain", {
			id : "selectUserDomainDialog",
			lock : true,
			opacity : 0.50, // 透明度
			title : "选择用户域",
			padding : 0,
			width : 360,
			height : 440,
			close : function() {
				var parentId = $.dialog.data('userDomainMoid');
				if (parentId != objectId.val()) {
					objectId.val(parentId);
					objectName.val($.dialog.data('userDomainName'));
				}
				
				if (groupName) {
					var gName = $.dialog.data('groupName');
					if (gName == null || gName == '') {
						gName = $.dialog.data('userDomainName');
					}
					groupName.val(gName);
				}
				
				if(typeof callback == "function"){
					callback();
			    }
			},
			cancel : false, // 隐藏关闭按钮
			drag : false
		// 不允许拖拽
		}, false);
	}
};