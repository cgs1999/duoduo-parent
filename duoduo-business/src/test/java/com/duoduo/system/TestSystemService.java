package com.duoduo.system;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.duoduo.system.service.TestLanguageService;
import com.duoduo.system.service.TestParameterService;
import com.duoduo.system.service.TestResourceService;
import com.duoduo.system.service.TestRoleService;
import com.duoduo.system.service.TestUserService;

/**
 * 测试System模块的Service
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:31:35
 * @version 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
		TestUserService.class, TestRoleService.class, TestResourceService.class, TestParameterService.class,
		TestLanguageService.class
})
public class TestSystemService {

}
