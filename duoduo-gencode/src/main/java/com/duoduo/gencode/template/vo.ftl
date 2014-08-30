package ${packageName}.vo;

import com.duoduo.core.util.DateUtils;
import com.duoduo.core.vo.BaseVO;
import ${packageName}.model.${beanName};

/**
 * TODO
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
public class ${beanName}VO extends BaseVO {

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

	/**
	 * TODO 将Entity封装为VO
	 * @return
	 */
	public static ${beanName}VO fromEntity(${beanName} entity) {
		${beanName}VO vo = new ${beanName}VO();
		vo.setId(entity.getId());
	<#list columns as item>
		vo.set${item.attributeName?cap_first}(entity.get${item.attributeName?cap_first}());
	</#list>
		vo.setCreateTime(DateUtils.toDatetimeString(entity.getCreateTime()));
		vo.setUpdateTime(DateUtils.toDatetimeString(entity.getUpdateTime()));
		return vo;
	}

	/**
	 * TODO 将VO封装为Entity
	 * @return
	 */
	public static ${beanName} toEntity(${beanName}VO vo) {
		${beanName} entity = new ${beanName}();
	<#list columns as item>
		entity.set${item.attributeName?cap_first}(vo.get${item.attributeName?cap_first}());
	</#list>
		return entity;
	}
}