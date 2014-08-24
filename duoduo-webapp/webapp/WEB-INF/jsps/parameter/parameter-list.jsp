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
	var baseUrl = "${BMC_APP_URL}/system/parameter";

	$(function() {
		Mo.gridDetail.init({
			baseUrl: baseUrl,
			datagridOptions : {
				url : baseUrl + "/getPageList",
				columns : [ [ {
					field : 'id',
					hidden : true
				}, {
					field : 'key',
					title : '参数标识',
					width : 80
				}, {
					field : 'name',
					title : '参数名称',
					width : 100
				}, {
					field : 'type',
					title : '参数类型',
					width : 80
				}, {
					field : 'value',
					title : '参数值',
					width : 150
				}, {
					field : 'memo',
					title : '备注',
					width : 150
				} ] ],

				onBeforeLoad:function(params){
					params.key = Mo.gridDetail.getMoInputVal("#key");
					params.name = Mo.gridDetail.getMoInputVal("#name");
					params.value = Mo.gridDetail.getMoInputVal("#value");
					params.start = (params.page-1)*params.rows;
					params.limit = params.rows;
				}
			}
		});
		
		Mo.SubFrame.controller.setDetailSize(56,0);
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
		<div class="search-bar">
			<input type="text" id="key" name="key" class="input-text" mo-hint="请输入参数标识" maxlength="20">
			<input type="text" id="name" name="name" class="input-text" mo-hint="参数名称" maxlength="20">
			<input type="text" id="value" name="value" class="input-text" mo-hint="参数值" maxlength="20">
			<div onclick="javascript: doSearch()" class="mo-icon mo-icon-search sep" ></div>
		</div>
		<div class="operate-bar">
			<a href="javascript: doCreate()" class="btn btn-x btn-gray">新建</a>
		</div>
	</div>
</body>
</html>