-- 初始化用户
INSERT INTO `sys_user` (id, account, name, password, salt, email, phone, status, create_time, update_time) VALUES 
('1', 'admin', '管理员', '21218cca77804d2ba1922c33e0151105', NULL, 'admin@duoduo.com', '15912345678', '1', '2014-03-12 18:57:07', '2014-03-12 18:57:07');

-- 初始化角色
INSERT INTO `sys_role`(id, name, type, memo, create_time, update_time) VALUES 
(1,'超级管理员','1','','2014-03-24 13:07:30','2014-04-09 05:25:59');

-- 初始化资源
INSERT INTO `sys_resource`(id, parent_id, name, url, order_index, create_time, update_time) VALUES 
(1,-1,'权限管理','',800,'2014-03-24 23:06:31','2014-04-16 07:47:40'),
(3,1,'用户管理','/system/user',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
(5,1,'菜单管理','/system/menu',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
(7,1,'角色管理','/system/role',930,'2014-03-24 23:14:43','2014-03-25 23:37:56');

-- 初始化角色资源
INSERT INTO `sys_role_resource`(role_id, resource_id) VALUES 
(1,1),(1,3),(1,5),(1,7);


-- 初始化默认管理员角色
INSERT INTO `sys_user_role`(user_id, role_id) VALUES 
(1,1);