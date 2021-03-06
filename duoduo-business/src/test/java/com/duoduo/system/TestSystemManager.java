package com.duoduo.system;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.duoduo.system.manager.TestLanguageManager;
import com.duoduo.system.manager.TestParameterManager;
import com.duoduo.system.manager.TestResourceManager;
import com.duoduo.system.manager.TestRoleManager;
import com.duoduo.system.manager.TestRoleResourceManager;
import com.duoduo.system.manager.TestUserManager;
import com.duoduo.system.manager.TestUserRoleManager;

/**
 * 测试System模块的Manager
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:52:24
 * @version 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
		TestUserManager.class, TestRoleManager.class, TestResourceManager.class, TestUserRoleManager.class,
		TestRoleResourceManager.class, TestParameterManager.class, TestLanguageManager.class
})
public class TestSystemManager {

}
