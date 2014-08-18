<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/resource.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title><spring:message code="home.head.title" /></title>
  <!-- 通用部分 -->
  <%@ include file="/inc/common/page_base_css.jsp"%>
  <%@ include file="/inc/common/page_base_js.jsp"%>
  <!-- 插件部分 -->
  <%@ include file="/inc/plugins/plugin_artdialog.jsp"%>
  <!-- 本地 -->
  <script type="text/javascript" src="${RESOUCE_STATIC_URL}/js/common.js?t=5.0.2486659314"></script>
  <script type="text/javascript" src="${RESOUCE_STATIC_URL}/js/mainFrame.js?t=5.0.283143827"></script>
  <script type="text/javascript" src="${RESOUCE_STATIC_URL}/css/main.css?t=5.0.283143827"></script>
 </head>
 
<script>
$.namespace("Mo.Config");
Mo.Config = {
	appUrl: "${BMC_APP_URL}",
	staticUrl:"${RESOUCE_STATIC_URL}",
	rootMenuId: ${rootMenuId},
	allMenus: ${allMenus}
};

$(function(){
    Mo.Home.init();
})
</script>

<body>
	<div id="wrap-all" class="wrap-all">
		<div id="header" class="hd-main">
			<div class="header-logo">
				<span class="ent"></span> <span class="sep">|</span> <span
					class="sys"><spring:message code="home.header.logo" /></span>
			</div>
			<div class="info">
				<a class="info-i user-info"><s></s>${currentUser.user.name }</a>
				<div class="info-i setting setting-info"
					title="<spring:message code='home.header.nav.setting' />">
					<span class="text setting"></span>
					<div class="pulldown setting-list" style="display: none">
						<div class="content">
							<span class="li"> <a id="modifyUser"
								href="javascript:void(0);"
								title="<spring:message code='home.header.nav.setting.user' />"><spring:message
										code='home.header.nav.setting.user' /></a>
							</span> <span class="li"> <a id="modifyPassword"
								href="javascript:void(0);"
								title="<spring:message code='home.header.nav.setting.password' />"><spring:message
										code='home.header.nav.setting.password' /></a>
							</span> <span class="li"> <a id="about"
								href="javascript:void(0);"
								title="<spring:message code='home.about.title' />"><spring:message
										code='home.about.title' /></a>
							</span>
						</div>
					</div>
				</div>
				<a class="info-i logout"
					title="<spring:message code='home.header.nav.logout' />"></a>
			</div>
		</div>

		<div id="inner-main" class="inner-main">
			<div id="inner-main-content" class="inner-main-content">
				<div id="main-nav" class="main-nav">
					<ul>
						<li><a herf="">导航1</a></li>
					</ul>
				</div>
				<div id="main-content">
					<iframe id="main-frame" src="welcome.jsp" name="main-frame"
						border="0" frameborder="no"></iframe>
				</div>
				<div id="detail-content" style="display: none;">
					<iframe id="detail-frame" src="welcome.jsp" name="detail-frame"
						border="0" frameborder="no"></iframe>
					<a id="e-bn-fold" class="icon" href="javascript:;"></a>
				</div>
			</div>
			<div id="aside" class="aside" style="overflow-y: hidden;">
				<div class="aside-content">
					<div class="search-div">
						<input type="text" class="search-input">
					</div>

					<div id="divAsideMenu" class="aside-menu"></div>
				</div>
			</div>
		</div>

		<div id="modifyUserWrapper" style="display: none;">
			<div class="mofiyUser-content">
				<div class="title">
					<span><spring:message code='home.user.modify.title' /></span> <a
						href="javascript:void(0);" class="W_close" hidefocus="true"></a> <input
						type="text" id="account" name="account" class="input-text"
						value="${currentUser.user.account }" /> <input type="hidden"
						id="oldName" name="oldName" value="${currentUser.user.name }" /> <input
						type="hidden" id="oldEmail" name="oldEmail"
						value="${currentUser.user.email }" />
				</div>
				<div class="separater"></div>
				<div class="info">
					<div class="setting-item">
						<label><spring:message
								code='home.user.modify.label.account' /></label>
						<div class="setting-item-main">
							<input type="text" id="name" name="name" class="input-text"
								value="${currentUser.user.name }" />
						</div>
					</div>
					<div class="setting-item">
						<label><spring:message code='home.user.modify.label.email' /></label>
						<div class="setting-item-main">
							<input type="text" id="email" name="email" class="input-text"
								value="${currentUser.user.email }" />
						</div>
					</div>
					<div class="item-msg">
						<div class="msg"></div>
					</div>
					<div class="btn-wrapper">
						<a class="btn-blue confirm btn-x btn-x-left"><spring:message
								code='home.user.modify.btn.confirm' /></a> <a
							class="btn-white cancel btn-x"><spring:message
								code='home.user.modify.btn.cancel' /></a>
					</div>
				</div>
			</div>
		</div>

		<div id="modifyPasswordWrapper" style="display: none;">
			<div class="mofidyPassword-content">
				<div class="title">
					<span><spring:message code='home.password.modify.title' /></span>
					<a href="javascript:void(0);" class="W_close" hidefocus="true"></a>
				</div>
				<div class="separater"></div>
				<div class="info">
					<div class="setting-item">
						<label><spring:message
								code='home.password.modify.label.oldPassword' /></label>
						<div class="setting-item-main">
							<input type="password" id="oldPassword" name="oldPassword"
								class="input-text item-old-password" value="" />
						</div>
					</div>
					<div class="setting-item">
						<label><spring:message
								code='home.password.modify.label.newPassword' /></label>
						<div class="setting-item-main">
							<input type="password" id="newPassword" name="newPassword"
								class="input-text item-new-password" value="" />
						</div>
					</div>
					<div class="password-message">
						<div>
							<span><spring:message
									code='home.password.modify.info.password.format' /></span>
						</div>
					</div>
					<div class="setting-item">
						<label><spring:message
								code='home.password.modify.label.conPassword' /></label>
						<div class="setting-item-main">
							<input type="password" id="confirmPassword"
								name="confirmPassword" class="input-text item-confirm-password"
								value="" />
						</div>
					</div>
					<div class="item-msg">
						<div class="msg"></div>
					</div>
					<div class="btn-wrapper">
						<a class="btn-blue confirm btn-x btn-x-left"><spring:message
								code='home.password.modify.btn.confirm' /></a> <a
							class="btn-white cancel btn-x"><spring:message
								code='home.password.modify.btn.cancel' /></a>
					</div>
				</div>
			</div>
		</div>

		<div id="aboutWrapper" style="display: none;">
			<div class="about-content">
				<div class="title">
					<span>关于</span> <a href="javascript:void(0);" class="W_close"
						hidefocus="true"></a>
				</div>
				<div class="separater"></div>
				<div class="info">
					<div class="company">
						<span>KEDACOM | 科达服务管理中心</span>
					</div>
					<div class="version">
						<span>版本号5.0.0.0</span>
					</div>
					<div class="corpright">
						<span>版权所有：2013069科达科技有限公司</span>
					</div>
				</div>
			</div>
		</div>

		<div id="logoutWrapper" style="display: none;">
			<div class="logout-content">
				<div class="title">
					<span>退出系统</span> <a href="javascript:void(0);" class="W_close"
						hidefocus="true"></a>
				</div>
				<div class="separater"></div>
				<div class="info">
					<div class="msg">
						<span>确认退出系统？</span>
					</div>
					<div class="btn-wrapper">
						<a class="btn-blue confirm btn-x btn-x-left">确认</a> <a
							class="btn-white cancel btn-x">取消</a>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
