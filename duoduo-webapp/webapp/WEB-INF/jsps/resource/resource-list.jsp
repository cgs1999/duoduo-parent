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
	var baseUrl = "${BMC_APP_URL}/system/resource";

	$(function() {
		Mo.gridDetail.init({
			baseUrl: baseUrl,
			treegridOptions : {
				treeField : 'name',
				url : baseUrl + "/getPageList",
				columns : [ [ {
					field : 'id',
					hidden : true
				}, {
					field : 'name',
					title : '资源名称',
					width : 100
				}, {
					field : 'type',
					title : '资源类型',
					width : 100,
					formatter : function(value,row,index){
						if(value==1) {
							return "菜单";
						}
						if(value==2) {
							return "操作";
						}
						return "URL";
					}
				}, {
					field : 'parentName',
					title : '上级资源',
					width : 100,
					hidden : true,
					formatter : function(value,row,index){
						if(row.parentId==null || row.parentId=="") {
							return "无";
						}
						return value;
					}
				}, {
					field : 'url',
					title : '链接地址',
					width : 150
				}, {
					field : 'orderIndex',
					title : '排序索引',
					width : 50
				} ] ],

				onBeforeLoad:function(row, params){
					params.name = Mo.gridDetail.getMoInputVal("#name");
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
		Mo.gridDetail.loadTreegrid();
	}
	
	function loadData() {
		// 用于表单操作后的列表刷新，采用reload可使datagrid保持在当前分页
		Mo.gridDetail.reloadTreegrid();
	}
</script>

<body id="grid-panel" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table id="data-grid"></table>
	</div>

	<div id="tb" class="inline">
		<div class="search-bar">
			<input type="text" id="name" name="name" class="input-text" mo-hint="请输入资源名称" maxlength="20">
			<div onclick="javascript: doSearch()" class="mo-icon mo-icon-search sep" ></div>
		</div>
		<div class="operate-bar"> 	
			<a href="javascript: doCreate()" class="btn btn-x btn-gray">新建</a>
		</div>
	</div>
</body>
</html>