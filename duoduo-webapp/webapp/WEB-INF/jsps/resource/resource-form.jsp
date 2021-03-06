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
  <%@ include file="/inc/plugins/plugin_artdialog.jsp"%>
  <!-- detail部分 -->
  <%@ include file="/inc/common/page_subFrame.jsp"%>
 </head>

<script type="text/javascript">
	var baseUrl = "${BMC_APP_URL}/system/resource";
	var parentId = ${parentId};
	var rootMenuId = ${rootMenuId};
	var rootMenus = ${rootMenus};
	
	$(function() {
		Mo.SubFrame.init();

		$('input[type="radio"]').ezMark();
		
		// 初始化上级资源
		initRootMenus();

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
					alert("保存成功");
					reloadDataGrid();
				} else {
					alert("保存失败，原因：" + t.description);
				}
				Mo.Base.throttle.unLock(url);
			}, "json").error(function() {
				alert("保存出现未知错误，请检查网络是否正常！");
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
				alert('记录不存在，删除失败');
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
					alert("删除成功");
					reloadDataGrid();
				} else {
					alert("删除失败，原因：" + t.description);
				}
				Mo.Base.throttle.unLock(url);
			}, "json").error(function() {
				alert("保存出现未知错误，请检查网络是否正常！");
				Mo.Base.throttle.unLock(url);
			});
		});

		// 取消
		$("#detail-btn-cancel").click(function() {
			Mo.SubFrame.controller.hideDetail();
		});
	})
	
	function beforeSave() {
		var data = {
			id : $('#id').val(),
			parentId : $('#parentId').val(),
			name : $.trim($('#name').val()),
			type : $('input:radio[name=type]:checked').val(),
			url : $.trim($('#url').val()),
			orderIndex : $.trim($('#orderIndex').val()),
			enable : $('input:radio[name=enable]:checked').val()
		};

		if (data.name == "") {
			alert('请输入资源名称');
			$('#name').focus();
			return false;
		}
		
		if (data.orderIndex != "" && !Mo.Base.validation.isUnsignedNumeric(data.orderIndex)) {
			alert('请填写正整数');
			$('#orderIndex').focus();
			return false;
		}

		return data;
	}
	
	function initRootMenus() {
		var html = '<option value="0">无</option>';
		var len = rootMenus.length;
		if(len>0) {
			for(var i=0; i<len; i++) {
				// 若当前资源为一级资源，则不显示自己
				if(rootMenus[i].id==$("#id").val()) {
					continue;
				}

				// 设置下拉选项并选中其上级资源
				html += '<option value="' + rootMenus[i].id + '"' + ((rootMenus[i].id==parentId) ? ' selected="selected"' : '') + '>' 
					+ rootMenus[i].name + '</option>';
			}
		}
		$("#parentId").html(html);
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
      <div class="title">资源信息</div>
    </div>

    
    <div class= "detail-content">
      <div id="input-all">
      	<input type="hidden" id='id' name="id"  value="${data.id}" />
        <ul id="editor-ui">
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">资源名称</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input"  id="name" name="name" value="${data.name}" maxlength="50" />
                    </div>
                </td>
                <td class="operate">
                  <div class="required"></div>
                </td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">上级资源</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <select id="parentId" name="parentId" class="e-input" style="width: 200px;"></select>
                    </div>
                </td>
                <td class="operate" style="padding: 4px;"></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">资源类型</td>
                <td class="input">
                    <div class="checkDiv">
                      <input type="radio" id="typeT" name="type" value="1" <c:if test="${data.type==1}"> checked</c:if> />
                      <label for="typeT">菜单</label>
                      <input type="radio" id="typeF" name="type" value="2" <c:if test="${data.type!=1}"> checked</c:if> />
                      <label for="typeF">URL</label>
                    </div>
                </td>
                <td class="operate"></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">链接地址</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input"  id="url" name="url" value="${data.url}" maxlength="250" />
                    </div>
                </td>
                <td class="operate"></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">排序索引</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input" id="orderIndex" name="orderIndex" value="${data.orderIndex}" maxlength="5" />
                    </div>
                </td>
                <td class="operate"></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">用户状态</td>
                <td class="input">
                    <div class="checkDiv">
                      <input type="radio" id="enableT" name="enable" value="1" <c:if test="${data.enable}"> checked</c:if> />
                      <label for="enableT">启用</label>
                      <input type="radio" id="enableF" name="enable" value="0" <c:if test="${!data.enable}"> checked</c:if> />
                      <label for="enableF">禁用</label>
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
	<div id="detail-btn-delete" class="btn-gray btn-x btn-x-left" <c:if test="${data.id == null}">style="display: none"</c:if>>删除</div>
	<div id="detail-btn-cancel" class="btn-gray btn-x">取消</div>
  </div>
</div>


</body>
</html>