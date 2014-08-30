package ${packageName}.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duoduo.core.vo.Page;
import ${packageName}.dao.${beanName}Dao;
import ${packageName}.model.${beanName};

/**
 * TODO 管理业务实现类
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
@Service("${beanName?uncap_first}Manager")
public class ${beanName}ManagerImpl implements ${beanName}Manager {

	@Resource
	private ${beanName}Dao ${beanName?uncap_first}Dao;

	@Override
	public ${beanName} getById(String id) {
		return ${beanName?uncap_first}Dao.getById(id);
	}

	@Override
	public ${beanName} getByName(String name) {
		return ${beanName?uncap_first}Dao.getByName(name);
	}

	@Override
	public List<${beanName}> listAll() {
		return ${beanName?uncap_first}Dao.listAll();
	}

	@Override
	public ${beanName} create(${beanName} ${beanName?uncap_first}) {
		return ${beanName?uncap_first}Dao.create(${beanName?uncap_first});
	}

	@Override
	public void update(${beanName} ${beanName?uncap_first}) {
		${beanName?uncap_first}Dao.update(${beanName?uncap_first});
	}

	@Override
	public boolean delete(String id) {
		return ${beanName?uncap_first}Dao.delete(id);
	}

	@Override
	public Page<${beanName}> pagingList(String searchKey, Page<${beanName}> page) {
		return ${beanName?uncap_first}Dao.pagingList(searchKey, page);
	}
}
