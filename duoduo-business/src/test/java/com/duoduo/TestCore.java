package com.duoduo;

import java.util.UUID;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 测试Core模块
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:28:22
 * @version 1.0.0
 */
public class TestCore {

	@Test
	public void test() {
		System.out.println(getClass());
	}

	@Test
	public void testMd5() {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		System.out.println("MD5(888888, null)=" + md5.encodePassword("888888", null));
		System.out.println("MD5(888888, 'admin')=" + md5.encodePassword("888888", "admin"));
		System.out.println("MD5(888888, 'cgs')=" + md5.encodePassword("888888", "cgs"));
		System.out.println("MD5(888888, 'chengesheng')=" + md5.encodePassword("888888", "chengesheng"));

		String uuid = UUID.randomUUID().toString();
		System.out.println("MD5(888888, '" + uuid + "')=" + md5.encodePassword("888888", uuid));
	}
}
