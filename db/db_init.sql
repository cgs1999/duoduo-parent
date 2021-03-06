-- 初始化用户
INSERT INTO `sys_user` (id, account, name, password, salt, email, phone, status, create_time, update_time) VALUES 
('1', 'admin', '管理员', '21218cca77804d2ba1922c33e0151105', NULL, 'admin@duoduo.com', '15912345678', '1', '2014-03-12 18:57:07', '2014-03-12 18:57:07');

-- 初始化角色
INSERT INTO `sys_role`(id, name, type, memo, create_time, update_time) VALUES 
(1,'超级管理员','1','','2014-03-24 13:07:30','2014-04-09 05:25:59');

-- 初始化资源
INSERT INTO `sys_resource`(id, parent_id, name, type, url, order_index, create_time, update_time) VALUES 
(10,0,'权限管理','1','',800,'2014-03-24 23:06:31','2014-04-16 07:47:40'),
/* 用户管理 */
(1010,10,'用户管理','1','/system/user/list',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
(101010,1010,'用户列表','2','/system/user/list',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
(101020,1010,'增加用户','2','/system/user/create',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
(101030,1010,'查看用户','2','/system/user/read',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
(101040,1010,'修改用户','2','/system/user/update',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
(101050,1010,'删除用户','2','/system/user/delete',950,'2014-03-24 23:14:26','2014-03-25 23:38:11'),
/* 资源管理 */
(1020,10,'资源管理','1','/system/resource/list',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
(102010,1020,'资源列表','2','/system/resource/list',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
(102020,1020,'增加资源','2','/system/resource/create',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
(102030,1020,'查看资源','2','/system/resource/read',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
(102040,1020,'修改资源','2','/system/resource/update',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
(102050,1020,'删除资源','2','/system/resource/delete',940,'2014-03-24 23:14:34','2014-03-25 23:38:19'),
/* 角色管理 */
(1030,10,'角色管理','1','/system/role/list',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(103010,1030,'角色列表','2','/system/role/list',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(103020,1030,'增加角色','2','/system/role/create',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(103030,1030,'查看角色','2','/system/role/read',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(103040,1030,'修改角色','2','/system/role/update',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(103050,1030,'删除角色','2','/system/role/delete',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),

(20,0,'系统设置','1','',800,'2014-03-24 23:06:31','2014-04-16 07:47:40'),
/* 参数配置 */
(2010,20,'参数配置','1','/system/parameter/list',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(201010,2010,'参数列表','2','/system/parameter/list',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(201020,2010,'增加参数','2','/system/parameter/create',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(201030,2010,'查看参数','2','/system/parameter/read',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(201040,2010,'修改参数','2','/system/parameter/update',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(201050,2010,'删除参数','2','/system/parameter/delete',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
/* 语言配置 */
(2020,20,'语言配置','1','/system/language/list',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(202010,2020,'语言列表','2','/system/language/list',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(202020,2020,'增加语言','2','/system/language/create',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(202030,2020,'查看语言','2','/system/language/read',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(202040,2020,'修改语言','2','/system/language/update',930,'2014-03-24 23:14:43','2014-03-25 23:37:56'),
(202050,2020,'删除语言','2','/system/language/delete',930,'2014-03-24 23:14:43','2014-03-25 23:37:56');




-- 初始化角色资源
INSERT INTO `sys_role_resource`(role_id, resource_id) VALUES 
(1,10),
(1,1010),(1,101010),(1,101020),(1,101030),(1,101040),(1,101050),
(1,1020),(1,102010),(1,102020),(1,102030),(1,102040),(1,102050),
(1,1030),(1,103010),(1,103020),(1,103030),(1,103040),(1,103050),
(1,20),
(1,2010),(1,201010),(1,201020),(1,201030),(1,201040),(1,201050),
(1,2020),(1,202010),(1,202020),(1,202030),(1,202040),(1,202050);


-- 初始化默认管理员角色
INSERT INTO `sys_user_role`(user_id, role_id) VALUES 
(1,1);



