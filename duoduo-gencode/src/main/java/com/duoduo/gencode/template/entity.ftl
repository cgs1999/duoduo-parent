package ${packageName}.model;

import java.util.Date;
import com.duoduo.core.model.IdEntity;

/**
 * 对应表 ${tableName}
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
public class ${beanName} extends IdEntity {

<#list columns as item>
	<#assign attrName="${item.attributeName}" >
	<#if attrName!="id" && attrName!="name" && attrName!="createTime" && attrName!="updateTime">
	/** ${item.comment}, 对应表中${item.columnName} */
	private ${item.attributeType} ${item.attributeName};
	</#if>
</#list>
<#list columns as item>
	<#assign attrName="${item.attributeName}" >
	<#if attrName!="id" && attrName!="name" && attrName!="createTime" && attrName!="updateTime">

	public ${item.attributeType} get${item.attributeName?cap_first}() {  
		return ${item.attributeName};
	}

	public void set${item.attributeName?cap_first}(${item.attributeType} ${item.attributeName}) {  
		this.${item.attributeName} = ${item.attributeName};
	}
	</#if>
</#list>
}