<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/resource.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title><spring:message code="test.test" /></title>
  <!-- 通用部分 -->
  <%@ include file="/inc/common/page_base_css.jsp"%>
  <%@ include file="/inc/common/page_base_js.jsp"%>
  <!-- 插件部分 -->
  <%@ include file="/inc/plugins/plugin_easyui.jsp"%>
  <!-- 其它 -->
  <%@ include file="/inc/common/page_subFrame.jsp"%>
 </head>

<script>
	var baseUrl = "${BMC_APP_URL}/system/user";

	$(function() {
		Mo.gridDetail.init({
			baseUrl: baseUrl,
			datagridOptions : {
				idField : "id",
				url : baseUrl + "/getPageList",
				columns : [ [ {
					field : 'id',
					hidden : true
				}, {
					field : 'name',
					title : '姓名',
					width : 100,
					formatter : function(value,row,index){
						return value ? value : row.account;
					}
				}, {
					field : 'email',
					title : '邮箱',
					width : 150
				}, {
					field : 'mobile',
					title : '手机',
					width : 100
				}, {
					field : 'officeLocation',
					title : '联系地址',
					width : 150
				}, {
					field : 'enable',
					title : '账号状态',
					width : 70,
					formatter : function(value,row,index){
						return value==1 ? "启用" : "禁用";
					}
				} ] ],

				onBeforeLoad:function(params){
					params.account = Mo.gridDetail.getMoInputVal("#account");
					params.name = Mo.gridDetail.getMoInputVal("#name");
					params.email = Mo.gridDetail.getMoInputVal("#email");
					params.phone = Mo.gridDetail.getMoInputVal("#phone");
					params.start = (params.page-1)*params.rows;
					params.limit = params.rows;
				}
			}
		});
		
		Mo.SubFrame.controller.setDetailSize(56,270);
	});

	function doCreate() {
		Mo.SubFrame.controller.setDetailFrame(baseUrl + "/create");
	}
	
	function doSearch() {
		// 加载数据
		Mo.gridDetail.load();
	}
	
	function loadData() {
		// 用于表单操作后的列表刷新，采用reload可使datagrid保持在当前分页
		Mo.gridDetail.reload();
	}
</script>

<body id="grid-panel" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table id="data-grid"></table>
	</div>

	<div id="tb" class="inline">
		<div class="search-bar toolbar">
			<input type="text" id="account" name="account" class="input-text" mo-hint="请输入帐号" maxlength="20">
			<input type="text" id="name" name="name" class="input-text sep" mo-hint="姓名" maxlength="20">
			<input type="text" id="email" name="email" class="input-text sep" mo-hint="邮箱" maxlength="20">
			<input type="text" id="phone" name="phone" class="input-text sep" mo-hint="手机" maxlength="20">
			<a href="javascript: doSearch()" class="easyui-linkbutton sep" iconCls="icon-search" plain="true"></a>
		</div>
		<div class="operate-bar">
			<a href="javascript: doCreate()" class="btn btn-x btn-gray">新建</a>
		</div>
	</div>
</body>
</html>