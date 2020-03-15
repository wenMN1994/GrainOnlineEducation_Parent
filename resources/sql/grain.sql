/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : grain

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/03/2020 20:11:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('1235829627104718850', '1235761399984693250', '第一章：Java入门', 1, '2020-03-06 07:28:38', '2020-03-06 09:06:47');
INSERT INTO `edu_chapter` VALUES ('1235830886662598658', '1235761399984693250', '第二章：控制台输入和输出', 2, '2020-03-06 07:33:39', '2020-03-06 07:33:39');
INSERT INTO `edu_chapter` VALUES ('1237924679566102529', '1235102212250763265', '第一章、SpringBoot_入门', 0, '2020-03-12 02:13:38', '2020-03-12 02:13:38');
INSERT INTO `edu_chapter` VALUES ('1237924907098705921', '1235102212250763265', '第二章、SpringBoot_配置', 1, '2020-03-12 02:14:32', '2020-03-12 02:14:32');
INSERT INTO `edu_chapter` VALUES ('1237925024765710337', '1235102212250763265', '第三章、SpringBoot_日志', 2, '2020-03-12 02:15:00', '2020-03-12 02:15:00');
INSERT INTO `edu_chapter` VALUES ('1237925175764848641', '1235102212250763265', '第四章、SpringBoot_web开发', 3, '2020-03-12 02:15:36', '2020-03-12 02:15:36');
INSERT INTO `edu_chapter` VALUES ('1237925298494377986', '1235102212250763265', '第五章、_SpringBoot_Docker', 4, '2020-03-12 02:16:05', '2020-03-12 02:16:05');
INSERT INTO `edu_chapter` VALUES ('1237928270829850625', '1235112137899212801', '第一章、SpringBoot高级-缓存', 0, '2020-03-12 02:27:54', '2020-03-12 02:27:54');
INSERT INTO `edu_chapter` VALUES ('1237928400547090433', '1235112137899212801', '第二章、SpringBoot高级-消息', 1, '2020-03-12 02:28:25', '2020-03-12 02:28:25');
INSERT INTO `edu_chapter` VALUES ('1237928516985163778', '1235112137899212801', '第三章、SpringBoot高级-检索', 2, '2020-03-12 02:28:53', '2020-03-12 02:28:53');
INSERT INTO `edu_chapter` VALUES ('1237928629132464130', '1235112137899212801', '第四章、SpringBoot高级-任务', 3, '2020-03-12 02:29:19', '2020-03-12 02:29:19');
INSERT INTO `edu_chapter` VALUES ('1237928741304930305', '1235112137899212801', '第五章、SpringBoot高级-安全', 4, '2020-03-12 02:29:46', '2020-03-12 02:29:46');
INSERT INTO `edu_chapter` VALUES ('1237928880799092737', '1235112137899212801', '第六章、SpringBoot高级-分布式', 5, '2020-03-12 02:30:19', '2020-03-12 02:30:19');

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程讲师ID',
  `subject_parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '一级分类ID',
  `subject_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程专业ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程标题',
  `price` decimal(10, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `view_count` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览数量',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Draft' COMMENT '视频状态 Draft未发布  Normal已发布',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subject_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('1235102212250763265', '1234056168213590018', '1234359260595417090', '1234359260788355074', 'Spring Boot视频教程（上）核心技术篇', 160.0000, 160, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/7422533c-6712-4e91-912a-bdcd36c45971.jpg', 0, 12, 1, 'Normal', '2020-03-04 07:18:09', '2020-03-14 11:21:11');
INSERT INTO `edu_course` VALUES ('1235112137899212801', '1233656219282042881', '1234359260595417090', '1234359260788355074', 'Spring Boot视频教程（下）整合篇', 160.0000, 150, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/e34b0397-1974-488c-9517-8650cd68a821.jpg', 0, 2, 1, 'Normal', '2020-03-04 07:57:36', '2020-03-12 06:11:39');
INSERT INTO `edu_course` VALUES ('1235126988562305026', '1232980830180122626', '1234359260217929729', '1234359260465393666', 'Vue技术全家桶', 178.0000, 100, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/a7a37107-57ea-406b-ad52-8987e1445506.jpg', 0, 0, 1, 'Normal', '2020-03-04 08:56:36', '2020-03-12 02:34:26');
INSERT INTO `edu_course` VALUES ('1235761399984693250', '1', '1234359260595417090', '1234359260645748737', '30天搞定Java核心技术（上）', 299.0000, 500, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/7733fbfb-29c8-48ac-abe3-254306c62279.jpg', 0, 0, 1, 'Normal', '2020-03-06 02:57:32', '2020-03-12 02:57:34');
INSERT INTO `edu_course` VALUES ('1237930738141761537', '2', '1234359260217929729', '1234359260289232897', 'HTML+CSS基础', 0.0000, 160, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/a2c162b5-b449-461c-9dbe-bdbf74bafeac.jpg', 0, 0, 1, 'Normal', '2020-03-12 02:37:42', '2020-03-12 02:37:47');
INSERT INTO `edu_course` VALUES ('1237931293408890881', '3', '1234359260838686721', '1234359260893212673', 'MySQL核心技术', 118.0000, 190, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/7eb029a1-556d-4464-91b3-77d3946657d3.jpg', 0, 0, 1, 'Normal', '2020-03-12 02:39:55', '2020-03-12 02:40:13');
INSERT INTO `edu_course` VALUES ('1237931894997913602', '7', '1234359260838686721', '1237931513635016705', 'MySQL高级', 138.0000, 150, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/936ddd14-a232-43c1-9e37-d37afcdb41db.jpg', 0, 0, 1, 'Normal', '2020-03-12 02:42:18', '2020-03-12 02:42:23');
INSERT INTO `edu_course` VALUES ('1237932992651464705', '9', '1234678569884475393', '1234678771345285121', 'Git&amp&GitHub', 78.0000, 120, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/2868106c-9c2e-4672-acb9-f07a7348ffec.jpg', 0, 0, 1, 'Normal', '2020-03-12 02:46:40', '2020-03-12 02:46:44');
INSERT INTO `edu_course` VALUES ('1237933619540529154', '6', '1234359260838686721', '1237933192304529410', 'Redis', 118.0000, 60, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/f6da4a18-0dfc-4f5d-87e2-5f41203ea66d.jpg', 0, 0, 1, 'Normal', '2020-03-12 02:49:09', '2020-03-12 02:49:13');
INSERT INTO `edu_course` VALUES ('1237934331175505922', '4', '1234359260595417090', '1237933857185599490', 'MyBatis', 118.0000, 90, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/c40d0add-a6ea-40d2-a824-bfa99064334d.jpg', 0, 0, 1, 'Normal', '2020-03-12 02:51:59', '2020-03-12 02:52:03');

-- ----------------------------
-- Table structure for edu_course_description
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_description`;
CREATE TABLE `edu_course_description`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '课程简介',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_course_description
-- ----------------------------
INSERT INTO `edu_course_description` VALUES ('1235102212250763265', '<p>SpringBoot是企业级开发的整体整合解决方案，特别用于快速构建微服务应用，旨在用最简单的方式让开发人员适应各种开发场景；<br />本视频着重介绍SpringBoot的使用和内部原理；内容包含微服务概念、配置文件、日志框架的使用、web开发、Thymeleaf模板引擎、Docker容器技术、MyBatis、Spring Data JPA、自定义starter等；<br />学习本套视频最基本需要掌握Spring、SpringMVC、Maven</p>', '2020-03-04 07:18:09', '2020-03-12 02:12:42');
INSERT INTO `edu_course_description` VALUES ('1235112137899212801', '<p>SpringBoot是企业级开发的整体整合解决方案，特别用于快速构建微服务应用，旨在用简单的方式让开发人员适应各种开发场景；<br />SpringBoot全套视频分为上下两部；<br />本视频《SpringBoot高级》属于下部，着重介绍SpringBoot的与各大场景的整合使用，内容包括：缓存（整合Redis），消息中间件（整合RabbitMQ），检索（整合ElasticSearch），任务（异步任务，定时任务，邮件任务），安全（整合SpringSecurity），分布式（整合Zookeeper/dubbo，整合SpringCloud），SpringBoot应用监管；<br />学习本套视频需要掌握SpringBoot；对于其他技术，视频包含快速入门讲解；</p>', '2020-03-04 07:57:36', '2020-03-12 02:26:55');
INSERT INTO `edu_course_description` VALUES ('1235126988562305026', '<p>本视频基于Vue2.5录制, 涵盖Vue开发所需技术: 模板、数据绑定、声明式渲染、计算属性、事件处理、过渡动画、指令、自定义Vue插件、组件化开发、组件间通信、Ajax前后台交互、Vue-Router 等. 对数据代理、模板解析、数据劫持、数据绑定核心部分进行深入的源码分析 。<br />视频中也涵盖Vue状态管理库Vuex的具体使用和原理结构分析。</p>', '2020-03-04 08:56:36', '2020-03-12 02:33:57');
INSERT INTO `edu_course_description` VALUES ('1235761399984693250', '<p>1.更适合零基础学员：<br />从Java语言起源开始，循序渐进，知识点剖析细致且每章配备大量随堂练习，让你步步为营，学得透彻、练的明白。<br />拒绝晦涩难懂的呆板教学，宋老师语言生动幽默，举例形象生动，深入浅出，迅速让你把握问题本质，四两拨千斤。</p>\n<p>2.课程内容推陈出新：<br />基于JDK 11，将Java8、Java9、Java10、Java11新特性一网打尽<br />课程中两种企业一线开发环境都使用：Eclipse（前七章）和IDEA(后十章）</p>\n<p>3.技术讲解更深入、更全面：<br />课程共十七章，715个知识视频小节，涉及主流Java使用的方方面面，全而不冗余。<br />全程内容涵盖数据结构、设计模式、JVM内存结构等深度技术<br />企业级笔试面试题目深入源码级讲解，拒绝死记硬背</p>\n<p>4.代码量更大、案例更丰富、更贴近实战：<br />Java语言基础阶段：12720行代码<br />Java语言高级阶段：11684行代码<br />课堂实战项目3套，课后实战项目2套<br />近百道企业面试真题精讲精练、极具实战性</p>', '2020-03-06 02:57:32', '2020-03-12 02:57:31');
INSERT INTO `edu_course_description` VALUES ('1237930738141761537', '<p>本套视频适合零基础并且对前端知识感兴趣的同学。内容涵盖HTML基础、标签、CSS 选择器、盒子模型、浮动、定位、图片整合、PS 切图等页面相关常用技术。 视频最后包含一个实战项目：将一个 PSD 设计图转换为一个商业网站的首页。让同学们体验前端页面开发的全过程。</p>\n<p>通过对该视频的学习，可以让同学对前端知识有一个完整的认识，并可以熟练掌握 PC 端的固定布局。</p>', '2020-03-12 02:37:42', '2020-03-12 02:37:42');
INSERT INTO `edu_course_description` VALUES ('1237931293408890881', '<p>Mysql是常用的关系型数据库管理系统，在WEB应用方面MySQL是很好的RDBMS(Relational Database Management System：关系数据库管理系统)应用软件之一。</p>\n<p>本视频涵盖MySQL核心技术主要知识点，每节知识配套对应练习。主要包含数据库和表的常用操作、约束、视图、存储过程和函数、流程控制结构以及综合运用各种命令实现数据的增删改查操作。</p>\n<p><span style=\"color: #ff0000;\"><strong>在本教程中，会让大家快速掌握Mysql的基本知识，并轻松使用Mysql数据库。</strong></span></p>', '2020-03-12 02:39:55', '2020-03-12 02:39:55');
INSERT INTO `edu_course_description` VALUES ('1237931894997913602', '<p>MySQL是目前常用的关系型数据库管理系统，在WEB应用方面 MySQL 也是目前很好的 RDBMS 应用软件之一。随着淘宝去IOE(去除IBM小型机、Oracle数据库及EMC存储设备)化的推进，MySQL 数据库在当前的互联网应用中变得越来越重要，本教程主要讲授针对 Java 开发所需的 MySQL 高级知识，课程中会让大家快速掌握索引，如何避免索引失效，索引的优化策略，了解innodb和myisam存储引擎，熟悉MySQL锁机制，能熟练配置MySQL主从复制，熟练掌握explain、show profile、慢查询日志等日常SQL诊断和性能分析策略。</p>', '2020-03-12 02:42:18', '2020-03-12 02:42:18');
INSERT INTO `edu_course_description` VALUES ('1237932992651464705', '<p>Git是先进的分布式版本控制系统，而Github是常用的Git代码托管中心。</p>\n<p>本套教程内容丰富、详实，囊括：Git安装过程、本地库基本操作、远程基本操作、基于分支的Gitflow工作流、跨团队协作的 Forking工作流、Eclipse中的Git版本控制以及Git对Eclipse特定文件忽略的配置方法。还通过展示Git内部版本管理机制，让你了解 到Git高效操作的底层逻辑。教程的最后完整演示了Gitlab服务器的搭建过程。</p>', '2020-03-12 02:46:40', '2020-03-12 02:46:40');
INSERT INTO `edu_course_description` VALUES ('1237933619540529154', '<p>Redis（REmote DIctionary Server）是一个key-value存储系统，是当下互联网公司常用的NoSQL数据库之一，是进入互联网行业的Java开发工程师必备技术。</p>\n<p>在本课程中，你将了解Redis是什么、能干什么、如何用，了解NoSQL的使用场景和概念，快速掌握Redis的安装配置、五大数据类型、常用操作命令、Redis持久化、主从复制、事务控制以及用Jedis操作进行Java开发等知识。</p>\n<p>尚硅谷Redis课程包含基础部分和高级部分。我们后续还会推出Redis视频的高级部分。</p>\n<p>&nbsp;&nbsp; &nbsp;高级部分将包含如下内容：<br />&nbsp;&nbsp; &nbsp;1 Redis的集群：Redis分片的缺点、集群架构、集群操作基本命令。<br />&nbsp;&nbsp; &nbsp;2 Lua脚本语言的介绍，<br />&nbsp;&nbsp; &nbsp;3 Redis和Lua结合,Redis的Lua脚本编程，构建强大的Redis服务。<br />&nbsp;&nbsp; &nbsp;4 Redis整合Spring等。<br />&nbsp;&nbsp; &nbsp;5 Redis集群实现Tomcat集群的Session共享等<br />&nbsp;&nbsp; &nbsp;......</p>', '2020-03-12 02:49:09', '2020-03-12 02:49:09');
INSERT INTO `edu_course_description` VALUES ('1237934331175505922', '<p>MyBatis是目前非常流行的一个轻巧、便利的持久化层框架。<br />本视频除对MyBatis日常基本使用示范外，还涉及诸多细节。以及较深入源码的讲解MyBatis运行原理、插件机制和一些企业实用场景。<br />视频包含：MyBatis配置文件编写，MyBatis动态SQL，MyBatis缓存机制，MyBatis-Spring整合，MyBatis逆向工程，MyBatis高级内容（MyBatis源码解析，MyBatis单/多插件运行机制，MyBatis四大对象工作原理，自定义TypeHandler、MyBatis存储过程&amp;游标处理等）。</p>', '2020-03-12 02:51:59', '2020-03-12 02:51:59');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程科目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1234359260217929729', 'H5前端', '0', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260289232897', 'HTML+CSS', '1234359260217929729', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260389896194', 'NodeJS', '1234359260217929729', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260465393666', 'VueJS', '1234359260217929729', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260595417090', 'Java', '0', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260645748737', 'JavaSE', '1234359260595417090', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260788355074', 'SpringBoot', '1234359260595417090', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260838686721', '数据库', '0', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359260893212673', 'MySQL核心', '1234359260838686721', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359261023236097', '大数据', '0', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234359261065179137', 'Hadoop', '1234359261023236097', 0, '2020-03-02 06:05:56', '2020-03-02 06:05:56');
INSERT INTO `edu_subject` VALUES ('1234414516654166017', 'Android', '0', 0, '2020-03-02 09:45:30', '2020-03-02 09:45:30');
INSERT INTO `edu_subject` VALUES ('1234678569884475393', '工具相关', '0', 0, '2020-03-03 03:14:45', '2020-03-03 03:14:45');
INSERT INTO `edu_subject` VALUES ('1234678630198566914', 'IDEA', '1234678569884475393', 0, '2020-03-03 03:14:59', '2020-03-03 03:14:59');
INSERT INTO `edu_subject` VALUES ('1234678771345285121', 'Git$GitHub', '1234678569884475393', 0, '2020-03-03 03:15:33', '2020-03-03 03:15:33');
INSERT INTO `edu_subject` VALUES ('1237927313555460098', 'SpringMVC', '1234359260595417090', 0, '2020-03-12 02:24:06', '2020-03-12 02:24:06');
INSERT INTO `edu_subject` VALUES ('1237931513635016705', 'MySQL高级', '1234359260838686721', 0, '2020-03-12 02:40:47', '2020-03-12 02:40:47');
INSERT INTO `edu_subject` VALUES ('1237933192304529410', 'Redis', '1234359260838686721', 0, '2020-03-12 02:47:27', '2020-03-12 02:47:27');
INSERT INTO `edu_subject` VALUES ('1237933857185599490', 'MyBatis', '1234359260595417090', 0, '2020-03-12 02:50:06', '2020-03-12 02:50:06');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师资历,一句话说明讲师',
  `career` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '讲师简介',
  `level` int(10) UNSIGNED NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1', '王老师', '熟练掌握Spring、SpringMVC、MyBatis、Redis、Dubbo、Zookeeper、SpringCloud等主流框架，擅长用简洁的方式 ，耐心细致地讲解JavaEE课程，通俗易懂。', ' 从事Java技术多年，拥有丰富的一线开发和教学经验,擅长JavaEE项目的研究与教学。曾参与或主持过网上理财P2P项目工程、有机食品的电商销售工程及网上点子众筹 、公司内部办公OA系统等。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/0afb5769-b04b-4daf-ace0-995730345254.png', 10, 0, '2018-03-30 17:15:57', '2020-03-12 03:41:22');
INSERT INTO `edu_teacher` VALUES ('10', '庞老师', '曾就职于北京信息情报研究所（Beijing Institute of information and information）,任高级软件研究员（Senior Software Researcher）与项目经理（Project Leader）,曾主持并参与《国家图书馆文献管理》《京包客》等系统开发。', '精通Java企业级开发，熟练应用目前主流的框架技术，以及独立的企业级搜索应用服务器应用。为多家企事业单位（如联想集团、现代汽车、国货航等）提供软件技术培训。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/f8a206a7-1526-4b82-849a-4c8f2ad270a5.png', 20, 0, '2018-04-03 14:32:19', '2020-03-12 03:45:03');
INSERT INTO `edu_teacher` VALUES ('1098969411110432769', 'string name', 'string intro', 'string career', 2, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/avatar/avatar.jpg', 10, 1, '2019-02-22 23:35:00', '2019-02-22 23:35:00');
INSERT INTO `edu_teacher` VALUES ('11', '张老师', '曾就职京东旗下网银在线、知名大数据企业北京百分点，曾主导或参与开发《网银在线支付对接系统》、《模型工厂BMW》、《舆情洞察系统》、《百分点用户拉通及用户画像》。学生在课堂上除了学到丰富的技术知识，还能接受最真实的企业的实战经验。主张以简代繁，即学即用。', '精通Linux、JavaEE、大数据技术栈、SpringBoot、SpringCloud，专注研究高并发、多线程、Spring源码分析。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/23010b7b-9913-4996-b766-447ac4f8cd3f.png', 10, 0, '2019-02-21 21:09:55', '2020-03-12 03:44:08');
INSERT INTO `edu_teacher` VALUES ('1232980830180122626', '沙老师', '毕业于中国石油大学，畅销书作者，国家认证生涯规划师，国内IT生涯规划领域先驱。具备多年的项目开发、管理和教育培训经验，曾参与邮政物流、电信网上营业厅等大型系统平台的研发工作，并主持研发某高校在线教育平台和教学实施保障系统等。', '精通Java、C、C++、PHP等计算机语言，对以Java语言为基础的各种框架有深入研究，对学生寄语：爱生活，爱Java；不抛弃，不放弃！', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/891859d2-b038-4c61-85dc-039053810875.png', 20, 0, '2020-02-27 10:48:32', '2020-03-12 03:45:56');
INSERT INTO `edu_teacher` VALUES ('1233656219282042881', '张老师', '具有多年软件开发经验，熟悉 Java、C/C++等多种开发语言，对主流框架Spring、Struts2、Hibernate、MyBatis、SpringMVC等具有丰富的开发经验。曾参与大型物联网系统（智能物流）、智慧城市系统的开发，并主持开发某社交软件的移动端（Android/iOS）、服务端（openfire）、桌面端（swing）、网页端（webIM），对即时通讯、图像处理、流媒体领域等有深入研究。', '中国人民大学软件工程硕士，曾先后就职于南天软件、用友金融、中植集团、百合贷，任技术经理、项目经理、技术部负责人。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/2cf0c8c9-3dc3-456b-90cd-f370c527d50e.png', 2, 0, '2020-02-29 07:32:18', '2020-03-12 03:48:29');
INSERT INTO `edu_teacher` VALUES ('1234056168213590018', '缪老师', '精通 Go、Hadoop、Spark、Android、Java、C/C++，对区块链、大数据、Linux 等有深入研究。技术功底深厚，热衷于新技术的研究。从业IT教育多年，学员多任职于腾讯、联想、京东、新浪、Oracle、IBM 等国内外一线互联网公司。人称大海哥，号称尚硅谷第一帅。', '辽工大外聘大数据、区块链专家、辽工大保送硕士，曾就职于交大思诺、文思海辉、宅急送等知名企业，历任项目经理、架构师 。多年项目开发、管理经验。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/ba93cfcf-ec4c-426a-83cc-6dbfba713801.png', 1, 0, '2020-03-01 10:01:33', '2020-03-12 03:38:16');
INSERT INTO `edu_teacher` VALUES ('2', '佟老师', ' 十年软件培训经验：曾在中国AOP全球外包排名第一的公司任Java、Oracle培训讲师、负责对公司员工新技术培训；曾任花旗银行特约JavaEE培训讲师；某部队研究所JavaEE培训；曾受聘为北航、厦门大学移动云计算专业教学主任， 并主讲Java、Android、iOS课程。', ' 资深项目经理、技术讲师、SUN SCJP、SCWCD、原工信部移动云计算教育培训中心教学总监。 十年软件开发经验：参与完成辽宁某高校远程教学管理系统、慧文信息门户系统、日本麦卡尔超市管理系统、崎玉市外来人口登记系统、深海视频会议管理系统、仙台市宫城县日常事务系统等项目的设计和开发。', 2, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/avatar/2020/03/12/cf70fc66-bebc-43bc-a782-21881b9c11c6.png', 1, 0, '2018-03-30 18:28:26', '2020-03-12 04:32:08');
INSERT INTO `edu_teacher` VALUES ('3', '韩老师', '主持或参与《新浪邮件系统》、《橙红sns网站》、《点击科技协同软件群组服务器端(Linux/solaris平台)》、《总参语音监控系统》、《英语学习机系统》、《用友erp(u8产品)系统》等项目。实战经验丰富，授课通俗易懂，多年来培训的学员已成功在用友、搜狐、淘宝、中软等知名企业就职。 ', '行业老兵，IT教育风云人物。清华毕业，拥有九年大型项目开发和管理经验，曾任新浪网软件项目经理，使用PHP与JavaEE技术开发新浪网系统，北京点击科技公司高级软件工程师，用友软件股份有限公司U8事业部软件工程师。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/25234015-57e3-4d5e-8ad8-38fd2123e825.png', 1, 0, '2018-03-31 09:20:46', '2020-03-12 03:34:23');
INSERT INTO `edu_teacher` VALUES ('4', '李老师', '先后出版过《UNIX系统管理实用教程》、《UNIX网络管理实用教程》、《Sun Solaris 8系统管理员指南》、《PHP5项目开发实战详解》、《完美应用Ubuntu》等多本计算机技术图书，国内多所大学外聘讲师，《开放系统世界》及《网络与运维》等杂志专栏作家。', '从业十七年，曾任即时Linux研究院副院长、PHPChina技术总监等职，国内早期的UNIX/Linux从业者，中国UNIX用户协会成员，首批红旗Linux认证讲师。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/fd5baa26-0f8b-4213-b870-314df12ba08d.png', 1, 0, '2018-04-03 14:13:51', '2020-03-12 03:33:52');
INSERT INTO `edu_teacher` VALUES ('5', '宋老师', '先后担任过高级软件开发工程师，系统架构师，高级讲师。具备丰富的软件开发经验和教学经验。精通C/C++、Java、Objective-C 等开发语言， 对JavaEE、Android开发有深入理解，对以Java语言为基础的各种框架有深入研究。亲自主持开发过多个大型项目，具备很强的项目管理能力和丰富的项目实施经验。', ' 东北师大理学学士，北京航空航天大学硕士，曾于北航软件开发环境国家重点实验室研究多项课题，对智能交通—浮动车海量数据挖掘及在线社交网络信息传播和控制问题有深入研究，曾发表论文数篇。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/b13e9a28-8d00-4519-921d-e110fcce83e2.png', 1, 0, '2018-04-03 14:15:36', '2020-03-12 03:33:22');
INSERT INTO `edu_teacher` VALUES ('6', '周老师', '历经电信、互联网金融等热门行业的项目历练，对传统JavaEE技术体系、云计算、大数据及当下热门的互联网技术都具有深厚的技术功底。 能够将企业流行、实用的技术带回课堂，引导学生少走弯路。', '多年一线开发及管理经验，曾先后就职于神华和信、亚信联创、安润金融等大中型互联网公司，任技术经理、项目经理、架构师等职位。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/5e732bd0-fa96-4645-a899-6923dc6ac9d2.png', 1, 0, '2018-04-01 14:19:28', '2020-03-12 03:37:21');
INSERT INTO `edu_teacher` VALUES ('7', '李老师', '精通JavaEE、Android、 C++、C#等开发语言，主持并参与Android SDK开发，熟练掌握JavaEE技术及架构体系、SpringMVC、Spring、 Mybatis、Redis、Struts2、Hibernate等框架。', '具有多年项目开发和教育培训经验，曾先后任职于致远协创、用友科技等知名企业，历任项目经理、架构师等，', 2, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/5ff3fe3f-b7c5-473d-ae0f-f58e25fbcfc6.png', 8, 0, '2018-04-03 14:21:03', '2020-03-12 03:39:40');
INSERT INTO `edu_teacher` VALUES ('8', '柴老师', '精通Oracle、MySQL、SSH、MyBatis、JBPM等众多框架，现专注于JavaSE技术的研究、教学和推广。“细心，耐心，用心”是一贯坚持的工作态度，追求“润物细无声”的教育方式。', '具有多年的教学和项目开发经验。曾参与国家电网内蒙古供电企业一体化管理信息系统的研发、国土资源局的国土资源非空间数据服务系统的研发、赛迪时代公司内部项目通用基础组件的设计与研发，并为新华南方等众多IT企业储备人才及广东、山东等多所高校大学生进行实训授课。', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/a4ccd78c-c090-425f-ab53-9bf75ae22d4d.png', 9, 0, '2018-04-03 14:23:06', '2020-03-12 03:40:30');
INSERT INTO `edu_teacher` VALUES ('9', '姚老师', '精通Java技术栈、web前端技术栈、PHP技术栈。十余年开发经验和教学经验，擅长全栈开发和授课。', '主持开发巴黎、卡塔尔防务展门户系统及展务管理及预定系统；主持开发黑龙江、吉林等地的CNCMAX宽带娱乐门户、IPTV增值服务计费系统、宽带价值链系统；主持开发黑龙江十三地市网通、哈尔滨市政府、大庆市政府、哈尔滨市八十余个委办局办公自动化系统', 1, 'https://grain-online-education.oss-cn-shenzhen.aliyuncs.com/cover/2020/03/12/139a51ea-e172-406b-a485-86b76948cc40.png', 10, 0, '2018-04-03 14:23:33', '2020-03-12 03:42:52');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `play_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '播放次数',
  `is_free` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0免费 1收费',
  `video_source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频原始文件名称',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '视频状态:见阿里云文档',
  `size` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程视频' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('1237925632360976386', '1235102212250763265', '1237924679566102529', '课时 1 : SpringBoot_入门-课程简介', 0, 0, 1, 'f86e5a8bfd0142b29710c6c63caafc9e', '6 - What If I Want to Move Faster.mp4', 0, '', 0, 1, '2020-03-12 02:17:25', '2020-03-12 06:07:28');
INSERT INTO `edu_video` VALUES ('1237925847046426626', '1235102212250763265', '1237924679566102529', '课时 2 : SpringBoot_入门-Spring Boot简介', 1, 0, 1, '', '', 0, '', 0, 1, '2020-03-12 02:18:16', '2020-03-12 02:18:16');
INSERT INTO `edu_video` VALUES ('1237925975937388546', '1235102212250763265', '1237924679566102529', '课时 3 : SpringBoot_入门-微服务简介', 2, 0, 1, '', '', 0, '', 0, 1, '2020-03-12 02:18:47', '2020-03-12 02:18:47');
INSERT INTO `edu_video` VALUES ('1237926139775291393', '1235102212250763265', '1237924907098705921', '课时 9 : SpringBoot_配置-yaml简介', 0, 0, 0, '', '', 0, '', 0, 1, '2020-03-12 02:19:26', '2020-03-12 02:19:26');
INSERT INTO `edu_video` VALUES ('1237926317810913282', '1235102212250763265', '1237924679566102529', '课时 4 : SpringBoot_入门-环境准备（1）', 3, 0, 0, '', '', 0, '', 0, 1, '2020-03-12 02:20:08', '2020-03-12 02:21:22');
INSERT INTO `edu_video` VALUES ('1237926505279524865', '1235102212250763265', '1237924679566102529', '课时 5 : SpringBoot_入门-环境准备（2）', 4, 0, 0, '', '', 0, '', 0, 1, '2020-03-12 02:20:53', '2020-03-12 02:21:29');
INSERT INTO `edu_video` VALUES ('1237926776072179714', '1235102212250763265', '1237924679566102529', '课时 6 : SpringBoot_入门-HelloWorld细节-场景启动器（starter）', 5, 0, 0, '', '', 0, '', 0, 1, '2020-03-12 02:21:58', '2020-03-12 02:21:58');
INSERT INTO `edu_video` VALUES ('1237926919467044866', '1235102212250763265', '1237924679566102529', '课时 7 : SpringBoot_入门-HelloWorld细节-自动配置', 6, 0, 0, '', '', 0, '', 0, 1, '2020-03-12 02:22:32', '2020-03-12 02:22:32');
INSERT INTO `edu_video` VALUES ('1237927044524412929', '1235102212250763265', '1237924679566102529', '课时 8 : SpringBoot_入门-使用向导快速创建Spring Boot应用', 7, 0, 0, '', '', 0, '', 0, 1, '2020-03-12 02:23:02', '2020-03-12 02:23:02');
INSERT INTO `edu_video` VALUES ('1237929063314231298', '1235112137899212801', '1237928270829850625', '课时 1 : SpringBoot高级-缓存-JSR107简介', 0, 0, 1, '', '', 0, '', 0, 1, '2020-03-12 02:31:03', '2020-03-12 02:31:03');
INSERT INTO `edu_video` VALUES ('1237929183187439618', '1235112137899212801', '1237928270829850625', '课时 2 : SpringBoot高级-缓存-Spring缓存抽象简介', 1, 0, 1, '', '', 0, '', 0, 1, '2020-03-12 02:31:32', '2020-03-12 02:31:32');

-- ----------------------------
-- Table structure for statistics_daily
-- ----------------------------
DROP TABLE IF EXISTS `statistics_daily`;
CREATE TABLE `statistics_daily`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `date_calculated` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '统计日期',
  `register_num` int(11) NOT NULL COMMENT '注册人数',
  `login_num` int(11) NOT NULL DEFAULT 0 COMMENT '登录人数',
  `video_view_num` int(11) NOT NULL DEFAULT 0 COMMENT '每日播放视频数',
  `course_num` int(11) NOT NULL DEFAULT 0 COMMENT '每日新增课程数',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `statistics_day`(`date_calculated`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网站统计日数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statistics_daily
-- ----------------------------
INSERT INTO `statistics_daily` VALUES ('1237213286814621698', '2020-01-01', 1, 179, 216, 171, '2020-03-10 03:06:49', '2020-03-10 03:06:49');
INSERT INTO `statistics_daily` VALUES ('1237213397753962497', '2020-01-02', 2, 192, 262, 111, '2020-03-10 03:07:15', '2020-03-10 03:07:15');
INSERT INTO `statistics_daily` VALUES ('1237213493346344961', '2020-01-16', 0, 111, 299, 191, '2020-03-10 03:07:38', '2020-03-10 03:07:38');
INSERT INTO `statistics_daily` VALUES ('1237213591170097153', '2020-01-19', 6, 131, 216, 176, '2020-03-10 03:08:01', '2020-03-10 03:08:01');
INSERT INTO `statistics_daily` VALUES ('1237226131132424194', '2020-03-10', 12, 153, 289, 154, '2020-03-10 03:57:51', '2020-03-10 03:57:51');
INSERT INTO `statistics_daily` VALUES ('1237277487016861698', '2019-03-16', 12, 100, 277, 124, '2020-03-10 07:21:55', '2020-03-10 07:21:55');

-- ----------------------------
-- Table structure for ucenter_member
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_member`;
CREATE TABLE `ucenter_member`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员id',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) UNSIGNED NULL DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ucenter_member
-- ----------------------------
INSERT INTO `ucenter_member` VALUES ('1080736474191646722', NULL, '13700000000', '96e79218965eb72c92a549dd5a330112', '用户nVexScJJoi', 1, 20, NULL, NULL, 0, 0, '2019-01-01 12:11:33', '2019-01-12 11:00:22');
INSERT INTO `ucenter_member` VALUES ('1080736474267144193', NULL, '13700000001', '96e79218965eb72c92a549dd5a330112', '用户XJtDfaYeKk', 1, 19, NULL, NULL, 0, 0, '2019-01-02 12:12:45', '2019-01-02 12:12:56');
INSERT INTO `ucenter_member` VALUES ('1080736474355224577', NULL, '13700000002', '96e79218965eb72c92a549dd5a330112', '用户wUrNkzAPrc', 1, 27, NULL, NULL, 0, 0, '2019-01-02 12:13:56', '2019-01-02 12:14:07');
INSERT INTO `ucenter_member` VALUES ('1086387099449442306', NULL, '13520191388', '96e79218965eb72c92a549dd5a330112', '用户XTMUeHDAoj', 2, 20, NULL, NULL, 0, 0, '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `ucenter_member` VALUES ('1086387099520745473', NULL, '13520191389', '96e79218965eb72c92a549dd5a330112', '用户vSdKeDlimn', 1, 21, NULL, NULL, 0, 0, '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `ucenter_member` VALUES ('1086387099608825858', NULL, '13520191381', '96e79218965eb72c92a549dd5a330112', '用户EoyWUVXQoP', 1, 18, NULL, NULL, 0, 0, '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `ucenter_member` VALUES ('1086387099701100545', NULL, '13520191382', '96e79218965eb72c92a549dd5a330112', '用户LcAYbxLNdN', 2, 24, NULL, NULL, 0, 0, '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `ucenter_member` VALUES ('1086387099776598018', NULL, '13520191383', '96e79218965eb72c92a549dd5a330112', '用户dZdjcgltnk', 2, 25, NULL, NULL, 0, 0, '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `ucenter_member` VALUES ('1086387099852095490', NULL, '13520191384', '96e79218965eb72c92a549dd5a330112', '用户wNHGHlxUwX', 2, 23, NULL, NULL, 0, 0, '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `ucenter_member` VALUES ('1106746895272849410', 'o1R-t5u2TfEVeVjO9CPGdHPNw-to', NULL, NULL, '檀梵\'', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/zZfLXcetf2Rpsibq6HbPUWKgWSJHtha9y1XBeaqluPUs6BYicW1FJaVqj7U3ozHd3iaodGKJOvY2PvqYTuCKwpyfQ/132', NULL, 0, 0, '2019-03-16 10:39:57', '2019-03-16 10:39:57');
INSERT INTO `ucenter_member` VALUES ('1106822699956654081', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2019-03-16 15:41:10', '2019-03-16 15:41:10');
INSERT INTO `ucenter_member` VALUES ('1106823035660357634', 'o1R-t5i4gENwHYRb5lVFy98Z0bdk', NULL, NULL, 'GaoSir', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJI53RcCuc1no02os6ZrattWGiazlPnicoZQ59zkS7phNdLEWUPDk8fzoxibAnXV1Sbx0trqXEsGhXPw/132', NULL, 0, 0, '2019-03-16 15:42:30', '2019-03-16 15:42:30');
INSERT INTO `ucenter_member` VALUES ('1106823041599492098', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2019-03-16 15:42:32', '2019-03-16 15:42:32');
INSERT INTO `ucenter_member` VALUES ('1106823115788341250', 'o1R-t5l_3rnbZbn4jWwFdy6Gk6cg', NULL, NULL, '换个网名哇、', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/jJHyeM0EN2jhB70LntI3k8fEKe7W6CwykrKMgDJM4VZqCpcxibVibX397p0vmbKURGkLS4jxjGB0GpZfxCicgt07w/132', NULL, 0, 0, '2019-03-16 15:42:49', '2019-03-16 15:42:49');
INSERT INTO `ucenter_member` VALUES ('1106826046730227714', 'o1R-t5gyxumyBqt0CWcnh0S6Ya1g', NULL, NULL, '我是Helen', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKDRfib8wy7A2ltERKh4VygxdjVC1x5OaOb1t9hot4JNt5agwaVLdJLcD9vJCNcxkvQnlvLYIPfrZw/132', NULL, 0, 0, '2019-03-16 15:54:28', '2019-03-16 15:54:28');
INSERT INTO `ucenter_member` VALUES ('1106828185829490690', 'o1R-t5nNlou5lRwBVgGNJFm4rbc4', NULL, NULL, ' 虎头', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKxCqRzuYWQmpwiaqQEjNxbC7WicebicXQusU306jgmfoOzUcFg1qaDq5BStiblwBjw5dUOblQ2gUicQOQ/132', NULL, 0, 0, '2019-03-16 16:02:58', '2019-03-16 16:02:58');
INSERT INTO `ucenter_member` VALUES ('1106830599651442689', 'o1R-t5hZHQB1cbX7HZJsiM727_SA', NULL, NULL, '是吴啊', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ9CsqApybcs7f3Dyib9IxIh0sBqJb7LicbjU4WticJFF0PVwFvHgtbFdBwfmk3H2t3NyqmEmVx17tRA/132', NULL, 0, 0, '2019-03-16 16:12:34', '2019-03-16 16:12:34');
INSERT INTO `ucenter_member` VALUES ('1106830976199278593', 'o1R-t5meKOoyEJ3-IhWRCBKFcvzU', NULL, NULL, '我才是Helen', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epMicP9UT6mVjYWdno0OJZkOXiajG0sllJTbGJ9DYiceej2XvbDSGCK8LCF7jv1PuG2uoYlePWic9XO8A/132', NULL, 0, 0, '2019-03-16 16:14:03', '2019-03-16 16:14:03');
INSERT INTO `ucenter_member` VALUES ('1106831936900415490', 'o1R-t5jXYSWakGtnUBnKbfVT5Iok', NULL, NULL, '文若姬', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/3HEmJwpSzguqqAyzmBwqT6aicIanswZibEOicQInQJI3ZY1qmu59icJC6N7SahKqWYv24GvX5KH2fibwt0mPWcTJ3fg/132', NULL, 0, 0, '2019-03-16 16:17:52', '2019-03-16 16:17:52');
INSERT INTO `ucenter_member` VALUES ('1106832491064442882', 'o1R-t5sud081Qsa2Vb2xSKgGnf_g', NULL, NULL, 'Peanut', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', NULL, 0, 0, '2019-03-16 16:20:04', '2019-03-16 16:20:04');
INSERT INTO `ucenter_member` VALUES ('1106833021442510849', 'o1R-t5lsGc3I8P5bDpHj7m_AIRvQ', NULL, NULL, '食物链终结者', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/MQ7qUmCprK9am16M1Ia1Cs3RK0qiarRrl9y8gsssBjIZeS2GwKSrnq7ZYhmrzuzDwBxSMMAofrXeLic9IBlW4M3Q/132', NULL, 0, 0, '2019-03-16 16:22:11', '2019-03-16 16:22:11');

SET FOREIGN_KEY_CHECKS = 1;
