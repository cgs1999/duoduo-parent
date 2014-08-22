<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/resource.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>Movision-BMC</title>
  <!-- 通用部分 -->
  <%@ include file="/inc/common/page_base_css.jsp"%>
  <%@ include file="/inc/common/page_base_js.jsp"%>
  <!-- 插件部分 -->
  <%@ include file="/inc/plugins/plugin_easyui.jsp" %>
  <!-- detail部分 -->
  <%@ include file="/inc/common/page_subFrame.jsp"%>
  <style type="text/css">
  .block-info {
  	width: 45%;
  	float: left;
  	margin: 5px;
  }
  </style>
 </head>
 
<script>
$.namespace("Mo.Config");
Mo.Config = {
  appUrl: "${BMC_APP_URL}",
  staticUrl:"${RESOUCE_STATIC_URL}"
};

$(function(){
	  Mo.SubFrame.init();
});
</script>

<script type="text/javascript">
function getStyleSheets() {
	var data = [];
	data[data.length] = {id: -1, text: "全部"};
	for ( var i = 0; i < document.styleSheets.length; i++) {
		var url = document.styleSheets[i].href;
		if (url) {
			data[data.length] = {
				id: i, 
				text: url.substr(url.lastIndexOf("/") + 1)
			};
		}
	}
	return data;
}

function getPrefixList() {
	var data = [];
	data[data.length] = {id: -1, text: "全部"};
	var tagList = ["","24","23","21","20","15","14","13","11"];
	for ( var i=0, len=tagList.length; i<len; i++) {
		data[data.length] = {
			id: i, 
			text: "mo-icon" + tagList[i]
		};
	}
	return data;
}

function getIcons(index, iconPrefix) {
	var data = [];

	var styleSheet = document.styleSheets[index];

	var rules;
	if (styleSheet.cssRules) {
		rules = styleSheet.cssRules;
	} else {
		rules = styleSheet.rules;
	}
	
	var fullPrefix = (iconPrefix=="全部") ? ".mo-icon" : "."+iconPrefix+"-";
	var prefixLen = fullPrefix.length;
	for ( var j = 0; j < rules.length; j++) {
		// 样式中，规约以".icon"开头的即为图标
		if (rules[j].selectorText.indexOf(":")!=-1 || rules[j].selectorText.substr(0, prefixLen) != fullPrefix) {
			continue;
		}
		
		data[data.length] = {
			id: index + "-" + j, 
			text: rules[j].selectorText.substr(1),
			iconCls: iconPrefix + " " + rules[j].selectorText.substr(1), 
			href: (styleSheet.href ? styleSheet.href : "")
		};
	}

	return data;
}

function getAllIcons(iconPrefix) {
	var data = [];
	for ( var i = 0; i < document.styleSheets.length; i++) {
		data = $.merge(data, getIcons(i, iconPrefix));
	}
	return data;
}

var lastCssIndex=-1;
var lastIconPrefix="全部";
$(function() {
	initGrid("icon-grid");

	$("#css-list").combotree({
		data: getStyleSheets(),
		onClick: function(node) {
			lastCssIndex=node.id;
			loadGridData("icon-grid", lastCssIndex, lastIconPrefix);
		},
		onLoadSuccess : function(node,data) {
			$('#css-list').combotree('setValue', lastCssIndex);
		}
	});

	$("#prefix-list").combotree({
		data: getPrefixList(),
		onClick: function(node) {
			lastIconPrefix=node.text;
			loadGridData("icon-grid", lastCssIndex, lastIconPrefix);
		},
		onLoadSuccess : function(node,data) {
			$('#prefix-list').combotree('setValue', lastIconPrefix);
		}
	});
	
	loadGridData("icon-grid", lastCssIndex, lastIconPrefix);
});

function initGrid(gridId) {
	$("#" + gridId).treegrid({
		idField: "id",
		treeField: "iconCls",
		rownumbers: true,
		striped: true,
		singleSelect: true,
		//width: 600,
		height: 250,
		border: false,
		fitColumns: true,
		autoRowHeight: true,
		columns: [[{
				field: "iconCls", 
				title: "IconCls", 
				halign: "center", 
				width: 60,
				formatter: function(value, row, index) {
					return "";
				},
				hidden: true
			}, {
				field: "id", 
				title: "ID", 
				halign: "center", 
				width: 60,
				hidden: true
			}, {
				field: "text", 
				title: "Text", 
				halign: "center", 
				width: 100
			}, {
				field: "href", 
				title: "Href", 
				halign: "center", 
				width: 200
			}
		]],
		toolbar: "#tb"
	});
}

function loadGridData(gridId, cssIndex, iconPrefix) {
	var data = cssIndex<0 ? getAllIcons(iconPrefix) : getIcons(cssIndex, iconPrefix);
	$("#" + gridId).treegrid("loadData", data);
}
</script>

<body border="0">

<div id="wrap-all" class="wrap-all">
	<div style="overflow: auto;">
		<div id="tb" style="height: 60px;">
			<div class="block-info">
				样式文件列表:<br />
				<div id="css-list" style="width: 245px;"></div>
			</div>
			<div class="block-info">
				样式前缀列表:<br />
				<div id="prefix-list" style="width: 245px;"></div>
			</div>
		</div>
		
		<div>
			<div id="icon-grid"></div>
		</div>
		
		<div>
			<div id="icon-preview">
				<div>
					<div>mo-icon30</div><hr />
					<div class="mo-icon mo-icon-search"></div>
					<div class="mo-icon mo-icon-open"></div>
					<div class="mo-icon mo-icon-edit"></div>
					<div class="mo-icon mo-icon-unbound"></div>
					<div class="mo-icon mo-icon-select-group"></div>
					<div class="mo-icon mo-icon-return"></div>
					<div class="mo-icon mo-icon-select-user"></div>
					<div class="mo-icon mo-icon-add"></div>
					<div class="mo-icon mo-icon-select-all"></div>
					<div class="mo-icon mo-icon-select2-right"></div>
					<div class="mo-icon mo-icon-remove2-left"></div>
					<div class="mo-icon mo-icon-remove-all"></div>
				</div>
				<div>
					<div>mo-icon24</div><hr />
					<div class="mo-icon24 mo-icon24-message"></div>
					<div class="mo-icon24 mo-icon24-setting"></div>
					<div class="mo-icon24 mo-icon24-exit"></div>
					<div class="mo-icon24 mo-icon24-default-portrait"></div>
				</div>
				<div>
					<div>mo-icon23</div><hr />
					<div class="mo-icon23 mo-icon23-close"></div>
				</div>
				<div>
					<div>mo-icon21</div><hr />
					<div class="mo-icon21 mo-icon21-prev-page"></div>
					<div class="mo-icon21 mo-icon21-next-page"></div>
				</div>
				<div>
					<div>mo-icon20</div><hr />
					<div class="mo-icon20 mo-icon20-dropdown"></div>
					<div class="mo-icon20 mo-icon20-search"></div>
					<div class="mo-icon20 mo-icon20-calendar"></div>
					<div class="mo-icon20 mo-icon20-add"></div>
				</div>
				<div>
					<div>mo-icon15</div><hr />
					<div class="mo-icon15 mo-icon15-combo"></div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
