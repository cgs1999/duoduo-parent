package com.duoduo.security.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.duoduo.system.manager.ResourceManager;
import com.duoduo.system.manager.RoleManager;
import com.duoduo.system.model.Resource;
import com.duoduo.system.model.Role;

/**
 * Spring Security安全数据源定义
 * @author chengesheng
 * @date 2014-6-6 下午2:49:41
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private PathMatcher pathMatcher = new AntPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private RoleManager roleManager;
	private ResourceManager resourceManager;

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public MyInvocationSecurityMetadataSource(RoleManager roleManager, ResourceManager resourceManager) {
		super();
		this.roleManager = roleManager;
		this.resourceManager = resourceManager;
		loadResourceDefine();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	private void loadResourceDefine() {
		resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();

		List<Role> roles = roleManager.listAll();
		if (roles != null && !roles.isEmpty()) {
			for (Role role : roles) {
				List<Resource> resources = resourceManager.listByRoleId("" + role.getId());
				if (roles != null && !roles.isEmpty()) {
					for (Resource resource : resources) {
						Collection<ConfigAttribute> configAttributes = null;
						ConfigAttribute configAttribute = new SecurityConfig("" + role.getId());
						if (resourceMap.containsKey(resource.getUrl())) {
							configAttributes = resourceMap.get(resource.getUrl());
							configAttributes.add(configAttribute);
						} else {
							configAttributes = new ArrayList<ConfigAttribute>();
							configAttributes.add(configAttribute);
							resourceMap.put(resource.getUrl(), configAttributes);
						}
					}
				}
			}
		}

		// 其他权限
		Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		list.add(new SecurityConfig("0"));
		resourceMap.put("/", list);
		resourceMap.put("/**", list);
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		for (String resUrl : resourceMap.keySet()) {
			// 注：resourceMap为LinkedHashMap，是有顺序的
			// PS：resourceMap所有的资源都是标准的url，这里不需处理，直接匹配即可
			if (pathMatcher.match(resUrl, url)) {
				return resourceMap.get(resUrl);
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
