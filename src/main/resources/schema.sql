-- ============================================
-- tlias 培训管理系统 - 扩展模块建表脚本
-- 运行前请确保 dept 表已存在
-- ============================================

-- 1. 员工表（关联部门表）
CREATE TABLE IF NOT EXISTS emp (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(32) NOT NULL COMMENT '姓名',
    gender TINYINT(1) DEFAULT 1 COMMENT '性别: 1男, 2女',
    phone VARCHAR(11) COMMENT '手机号',
    email VARCHAR(64) COMMENT '邮箱',
    dept_id INT COMMENT '所属部门ID',
    position VARCHAR(64) COMMENT '职位',
    create_time DATE COMMENT '创建时间',
    update_time DATE COMMENT '更新时间',
    FOREIGN KEY (dept_id) REFERENCES dept(id) ON DELETE SET NULL
) COMMENT '员工表';

-- 2. 学员表
CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(32) NOT NULL COMMENT '姓名',
    gender TINYINT(1) DEFAULT 1 COMMENT '性别: 1男, 2女',
    phone VARCHAR(11) COMMENT '手机号',
    email VARCHAR(64) COMMENT '邮箱',
    education VARCHAR(32) COMMENT '学历',
    major VARCHAR(64) COMMENT '专业',
    create_time DATE COMMENT '创建时间',
    update_time DATE COMMENT '更新时间'
) COMMENT '学员表';

-- 3. 教师表
CREATE TABLE IF NOT EXISTS teacher (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(32) NOT NULL COMMENT '姓名',
    gender TINYINT(1) DEFAULT 1 COMMENT '性别: 1男, 2女',
    phone VARCHAR(11) COMMENT '手机号',
    email VARCHAR(64) COMMENT '邮箱',
    specialty VARCHAR(64) COMMENT '专业方向/擅长领域',
    create_time DATE COMMENT '创建时间',
    update_time DATE COMMENT '更新时间'
) COMMENT '教师表';
