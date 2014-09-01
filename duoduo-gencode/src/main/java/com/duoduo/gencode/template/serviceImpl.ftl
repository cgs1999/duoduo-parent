package ${packageName}.service;

import java.util.ArrayList;
import java.util.List;
<#assign beanName="${beanName}" >
<#if beanName!="Resource">

import javax.annotation.Resource;

</#if>
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoduo.core.vo.Page;
import ${packageName}.manager.${beanName}Manager;
import ${packageName}.model.${beanName};
import ${packageName}.vo.${beanName}VO;

/**
 * TODO 管理业务实现类
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
@Transactional
@Service("${beanName?uncap_first}Service")
public class ${beanName}ServiceImpl implements ${beanName}Service {

	@<#if beanName=="Resource">javax.annotation.</#if>Resource
	private ${beanName}Manager ${beanName?uncap_first}Manager;

	@Override
	public ${beanName}VO getById(String id) {
		${beanName} ${beanName?uncap_first} = ${beanName?uncap_first}Manager.getById(id);
		if (${beanName?uncap_first} == null) {
			return null;
		}
		return ${beanName}VO.fromEntity(${beanName?uncap_first});
	}

	@Override
	public ${beanName}VO getByName(String name) {
		${beanName} ${beanName?uncap_first} = ${beanName?uncap_first}Manager.getByName(name);
		if (${beanName?uncap_first} == null) {
			return null;
		}
		return ${beanName}VO.fromEntity(${beanName?uncap_first});
	}

	@Override
	public List<${beanName}VO> listAll() {
		return fromEntityList(${beanName?uncap_first}Manager.listAll());
	}

	@Override
	public ${beanName}VO create(${beanName}VO ${beanName?uncap_first}VO) {
		${beanName} ${beanName?uncap_first} = ${beanName?uncap_first}Manager.create(${beanName}VO.toEntity(${beanName?uncap_first}VO));
		if (${beanName?uncap_first} == null) {
			return null;
		}

		return ${beanName}VO.fromEntity(${beanName?uncap_first});
	}

	@Override
	public void update(${beanName}VO ${beanName?uncap_first}VO) {
		${beanName?uncap_first}Manager.update(${beanName}VO.toEntity(${beanName?uncap_first}VO));
	}

	@Override
	public boolean delete(String id) {
		return ${beanName?uncap_first}Manager.delete(id);
	}

	@Override
	public Page<${beanName}VO> pagingList(String searchKey, Page<${beanName}VO> page) {
		Page<${beanName}> entityPage = ${beanName?uncap_first}Manager.pagingList(searchKey, toEntityPage(page));

		return fromEntityPage(entityPage);
	}

	/**
	 * 转换Page &lt;VO&gt; 为 Page &lt;Entity&gt;
	 * @param voPage
	 * @return
	 */
	private Page<${beanName}> toEntityPage(Page<${beanName}VO> voPage) {
		Page<${beanName}> entityPage = new Page<${beanName}>();
		entityPage.setStart(voPage.getStart());
		entityPage.setLimit(voPage.getLimit());
		entityPage.setTotal(voPage.getTotal());
		entityPage.setSort(voPage.getSort());
		entityPage.setDir(voPage.getDir());
		return entityPage;
	}

	/**
	 * 转换Page &lt;Entity&gt; 为 Page &lt;VO&gt;
	 * @param entityPage
	 * @return
	 */
	private Page<${beanName}VO> fromEntityPage(Page<${beanName}> entityPage) {
		Page<${beanName}VO> voPage = new Page<${beanName}VO>();
		voPage.setStart(entityPage.getStart());
		voPage.setLimit(entityPage.getLimit());
		voPage.setTotal(entityPage.getTotal());
		voPage.setRows(fromEntityList(entityPage.getRows()));
		voPage.setFooter(fromEntityList(entityPage.getFooter()));
		voPage.setSort(entityPage.getSort());
		voPage.setDir(entityPage.getDir());
		return voPage;
	}

	/**
	 * 转换List &lt;Entity&gt; 为 List &lt;VO&gt;
	 * @param voList
	 * @return
	 */
	private List<${beanName}VO> fromEntityList(List<${beanName}> entityList) {
		List<${beanName}VO> voList = new ArrayList<${beanName}VO>(0);
		if (entityList != null && !entityList.isEmpty()) {
			for (${beanName} entity : entityList) {
				voList.add(${beanName}VO.fromEntity(entity));
			}
		}
		return voList;
	}
}
