package com.duoduo.system;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.duoduo.system.dao.TestResourceDao;
import com.duoduo.system.dao.TestRoleDao;
import com.duoduo.system.dao.TestRoleResourceDao;
import com.duoduo.system.dao.TestUserDao;
import com.duoduo.system.dao.TestUserRoleDao;

/**
 * 测试System模块的Dao
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:30:29
 * @version 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
		TestUserDao.class, TestRoleDao.class, TestResourceDao.class, TestUserRoleDao.class, TestRoleResourceDao.class
})
public class TestSystemDao {

}
