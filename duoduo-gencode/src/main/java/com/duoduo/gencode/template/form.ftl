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
  <%@ include file="/inc/plugins/plugin_artdialog.jsp"%>
  <!-- detail部分 -->
  <%@ include file="/inc/common/page_subFrame.jsp"%>
 </head>

<script type="text/javascript">
	var baseUrl = "${'$\{BMC_APP_URL}'}/system/language";
	
	$(function() {
		Mo.SubFrame.init();
		$('input[type="radio"]').ezMark();

		// 保存
		$("#detail-btn-save").click(function() {
			var url = baseUrl + "/save";
			if (Mo.Base.throttle.isLock(url)) {
				return false;
			}
			
			Mo.Base.throttle.lock(url); // 正在处理中...
			
			var data = beforeSave();
	    	if(data == false){
				Mo.Base.throttle.unLock(url);
	        	return;
	        }
	        
			$.post(url, data, function(t) {
				if (t.success) {
					Mo.SubFrame.controller.hideDetail();
					$.dialog.tips("保存成功");
					reloadDataGrid();
				} else {
					$.dialog.tips("保存失败，原因：" + t.description);
				}
				Mo.Base.throttle.unLock(url);
			}, "json").error(function() {
				$.dialog.tips("保存出现未知错误，请检查网络是否正常！");
				Mo.Base.throttle.unLock(url);
			});
		});

		// 删除
		$("#detail-btn-delete").click(function() {
			var url = baseUrl + "/delete";
			if (Mo.Base.throttle.isLock(url)) {
				return false;
			}
			
			Mo.Base.throttle.lock(url); // 正在处理中...

			var id = $('#id').val();
			if(!id) {
				$.dialog.tips('记录不存在，删除失败');
				Mo.Base.throttle.unLock(url);
				return;
			}
			
			if(!confirm('确定要删除吗？')) {
				Mo.Base.throttle.unLock(url);
				return;
			}
					
			$.post(url, {
				id : id
			}, function(t) {
				if (t.success) {
					Mo.SubFrame.controller.hideDetail();
					$.dialog.tips("删除成功");
					reloadDataGrid();
				} else {
					$.dialog.tips("删除失败，原因：" + t.description);
				}
				Mo.Base.throttle.unLock(url);
			}, "json").error(function() {
				$.dialog.tips("保存出现未知错误，请检查网络是否正常！");
				Mo.Base.throttle.unLock(url);
			});
		});

		// 取消
		$("#detail-btn-cancel").click(function() {
			Mo.SubFrame.controller.hideDetail();
		});
	});
	
	function beforeSave() {
		var data = {
			id : $('#id').val(),
<#list columns as item>
	<#assign attrName="${item.attributeName}" >
	<#if attrName!="id" && attrName!="enable" && attrName!="createTime" && attrName!="updateTime">
			${item.attributeName} : $.trim($('#${item.attributeName}').val()),
	</#if>
</#list>
			enable : $('input:radio[name=enable]:checked').val()
		};

		if (data.name == "") {
			$.dialog.tips('请输入名称');
			$('#name').focus();
			return false;
		}
		
		// TODO

		return data;
	}
	
	function reloadDataGrid() {
		// 刷新datagrid的数据
    	Mo.SubFrame.controller.callMainFrameFn("loadData");
	}
</script>

<body>
<div id="wrap-all" class="wrap-detail">
	<div class="detail-body">
    <div class="detail-header">
      <div class="title">${beanName}信息</div>
    </div>
    
    
    <div class= "detail-content">
      <div id="input-all">
      	<input type="hidden" id='id' name="id"  value="${'$\{data.id}'}" />
        <ul id="editor-ui">
<#list columns as item>
	<#assign attrName="${item.attributeName}" >
	<#if attrName!="id" && attrName!="enable" && attrName!="createTime" && attrName!="updateTime">
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">${item.comment}</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input" type="text" id="${item.attributeName}" name="${item.attributeName}" value="${'$\{data.'}${item.attributeName}}">
                    </div>
                </td>
                <td class="operate"><div class="required"></div></td>
              </tr>
            </table>
          </li>
          
	</#if>
</#list>
          <!-- TODO -->
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">启停状态</td>
                <td class="input">
                    <div class="checkDiv">
                      <input type="radio" id="enableT" name="enable" value="1" <c:if test="${'$\{data.enable==1}'}"> checked</c:if> />
                      <label for="enableT">启用</label>
                      <input type="radio" id="enableF" name="enable" value="0" <c:if test="${'$\{data.enable!=1}'}"> checked</c:if> />
                      <label for="enableF">停用</label>
                    </div>
                </td>
                <td class="operate"></td>
              </tr>
            </table>
          </li>
        </ul>
    </div>
  </div>
  </div>
  <div class="detail-btn">
	<div id="detail-btn-save" class="btn-gray btn-x btn-x-left">保存</div>
	<div id="detail-btn-delete" class="btn-gray btn-x btn-x-left"  <c:if test="${'$\{data.id == null}'}">style="display: none"</c:if>>删除</div>
	<div id="detail-btn-cancel" class="btn-gray btn-x">取消</div>
  </div>
</div>


</body>
</html>