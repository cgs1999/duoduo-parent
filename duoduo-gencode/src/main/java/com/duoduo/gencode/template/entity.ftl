package ${packageName};

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
	/** ${item.comment}, 对应表中${item.columnName} */
	private ${item.attributeType} ${item.attributeName};
</#list>
<#list columns as item>

	public ${item.attributeType} get${item.attributeName?cap_first}() {  
		return ${item.attributeName};
	}

	public void set${item.attributeName?cap_first}(${item.attributeType} ${item.attributeName}) {  
		this.${item.attributeName} = ${item.attributeName};
	}
</#list>
}