package com.duoduo.security.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 判断用户拥有权限和请求URL权限是否匹配
 * @author chengesheng
 * @date 2014-6-6 下午2:30:18
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	// In this method, need to compare authentication with configAttributes.
	// 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
	// 2, Check authentication has attribute in permission configuration(configAttributes)
	// 3, If not match corresponding authentication, throw a AccessDeniedException.
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		// System.out.println(object.toString()); // object is a URL.
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			Collection<GrantedAuthority> authorities = null;
			if ("anonymousUser".equals(authentication.getPrincipal())) {
				authorities = collection2List(authentication.getAuthorities());
			} else {
				authorities = collection2List(((UserDetails) authentication.getPrincipal()).getAuthorities());
			}
			for (GrantedAuthority ga : authorities) {
				// System.out.println(needRole.equals(ga.getAuthority()));
				if (("0".equals(needRole) && !"anonymousUser".equals(authentication.getPrincipal()))
						|| needRole.equals(ga.getAuthority())) { // ga is user's role.
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 将Collection<GrantedAuthority>转化为ArrayList<GrantedAuthority>
	 * @param authorities
	 * @return
	 */
	private ArrayList<GrantedAuthority> collection2List(Collection<? extends GrantedAuthority> authorities) {
		ArrayList<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>(0);
		if (authorities != null && !authorities.isEmpty()) {
			for (GrantedAuthority authority : authorities) {
				authorityList.add(authority);
			}
		}
		return authorityList;
	}

}
