package com.duoduo.system.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duoduo.core.vo.Page;
import com.duoduo.system.dao.ParameterDao;
import com.duoduo.system.model.Parameter;

/**
 * 参数管理业务实现类
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午12:23:59
 * @version 1.0.0
 */
@Service("parameterManager")
public class ParameterManagerImpl implements ParameterManager {

	@Resource
	private ParameterDao parameterDao;

	@Override
	public Parameter getById(String id) {
		return parameterDao.getById(id);
	}

	@Override
	public Parameter getByKey(String key) {
		return parameterDao.getByKey(key);
	}

	@Override
	public List<Parameter> listByType(String type) {
		return parameterDao.listByType(type);
	}

	@Override
	public Parameter create(Parameter parameter) {
		return parameterDao.create(parameter);
	}

	@Override
	public void update(Parameter parameter) {
		parameterDao.update(parameter);
	}

	@Override
	public boolean delete(String id) {
		return parameterDao.delete(id);
	}

	@Override
	public Page<Parameter> pagingList(String key, Page<Parameter> page) {
		return parameterDao.pagingList(key, page);
	}

	@Override
	public Page<Parameter> pagingList(String key, String name, String value, Page<Parameter> page) {
		return parameterDao.pagingList(key, name, value, page);
	}
}
