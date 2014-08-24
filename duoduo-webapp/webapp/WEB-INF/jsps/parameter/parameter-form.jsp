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
	var baseUrl = "${BMC_APP_URL}/system/parameter";
	
	$(function() {
		Mo.SubFrame.init();

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
			key : $.trim($('#key').val()),
			name : $.trim($('#name').val()),
			type : $.trim($('#type').val()),
			value : $.trim($('#value').val()),
			memo : $('#memo').val()
		};

		if (data.key == "") {
			$.dialog.tips('请输入参数标识');
			$('#key').focus();
			return false;
		}

		if (data.name == "") {
			$.dialog.tips('请输入参数名称');
			$('#name').focus();
			return false;
		}

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
      <div class="title">参数信息</div>
    </div>
    
    
    <div class= "detail-content">
      <div id="input-all">
      	<input type="hidden" id='id' name="id"  value="${data.id}" />
        <ul id="editor-ui">
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">参数标识</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input" type="text" id="key" name="key" value="${data.key}">
                    </div>
                </td>
                <td class="operate"><div class="required"></div></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">参数名称</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input" type="text" id="name" name="name" value="${data.name}">
                    </div>
                </td>
                <td class="operate"><div class="required"></div></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">参数类型</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input" type="text" id="type" name="type" value="${data.type}">
                    </div>
                </td>
                <td class="operate"><div class="required"></div></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">参数内容</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <input class="e-input" type="text" id="value" name="value" value="${data.value}">
                    </div>
                </td>
                <td class="operate"><div class="required"></div></td>
              </tr>
            </table>
          </li>
          <li class="data-li">
            <table style="table-layout:fixed">
            <tbody>
              <tr>
                <td class="title">备　　注</td>
                <td class="input">
                    <div style="padding-right:10px;position:relative;">
                      <textarea rows="3" cols="70" id="memo" name="memo" class="e-textarea">${data.memo}</textarea>
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
	<div id="detail-btn-delete" class="btn-gray btn-x btn-x-left"  <c:if test="${data.id == null}">style="display: none"</c:if>>删除</div>
	<div id="detail-btn-cancel" class="btn-gray btn-x">取消</div>
  </div>
</div>


</body>
</html>