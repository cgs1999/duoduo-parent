package com.duoduo.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoduo.core.vo.Page;
import com.duoduo.system.manager.ParameterManager;
import com.duoduo.system.model.Parameter;
import com.duoduo.system.vo.ParameterVO;

/**
 * 参数管理业务实现类
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午12:23:59
 * @version 1.0.0
 */
@Transactional
@Service("parameterService")
public class ParameterServiceImpl implements ParameterService {

	@Resource
	private ParameterManager parameterManager;

	@Override
	public ParameterVO getById(String id) {
		Parameter parameter = parameterManager.getById(id);
		if (parameter == null) {
			return null;
		}
		return ParameterVO.fromEntity(parameter);
	}

	@Override
	public ParameterVO getByKey(String key) {
		Parameter parameter = parameterManager.getByKey(key);
		if (parameter == null) {
			return null;
		}
		return ParameterVO.fromEntity(parameter);
	}

	@Override
	public List<ParameterVO> listByType(String type) {
		return fromEntityList(parameterManager.listByType(type));
	}

	@Override
	public ParameterVO create(ParameterVO parameterVO) {
		Parameter parameter = parameterManager.create(ParameterVO.toEntity(parameterVO));
		if (parameter == null) {
			return null;
		}

		return ParameterVO.fromEntity(parameter);
	}

	@Override
	public void update(ParameterVO parameterVO) {
		parameterManager.update(ParameterVO.toEntity(parameterVO));
	}

	@Override
	public boolean delete(String id) {
		return parameterManager.delete(id);
	}

	@Override
	public Page<ParameterVO> pagingList(String key, Page<ParameterVO> page) {
		Page<Parameter> entityPage = parameterManager.pagingList(key, toEntityPage(page));

		return fromEntityPage(entityPage);
	}

	@Override
	public Page<ParameterVO> pagingList(String key, String name, String value, Page<ParameterVO> page) {
		Page<Parameter> entityPage = parameterManager.pagingList(key, name, value, toEntityPage(page));

		return fromEntityPage(entityPage);
	}

	/**
	 * 转换Page &lt;VO&gt; 为 Page &lt;Entity&gt;
	 * @param voPage
	 * @return
	 */
	private Page<Parameter> toEntityPage(Page<ParameterVO> voPage) {
		Page<Parameter> entityPage = new Page<Parameter>();
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
	private Page<ParameterVO> fromEntityPage(Page<Parameter> entityPage) {
		Page<ParameterVO> voPage = new Page<ParameterVO>();
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
	private List<ParameterVO> fromEntityList(List<Parameter> entityList) {
		List<ParameterVO> voList = new ArrayList<ParameterVO>(0);
		if (entityList != null && !entityList.isEmpty()) {
			for (Parameter entity : entityList) {
				voList.add(ParameterVO.fromEntity(entity));
			}
		}
		return voList;
	}

}
