# 劲课堂 服务端
## 简介
&nbsp;&nbsp;&nbsp;&nbsp;
本项目采用Spring-Boot+MyBatis开发
## 基本实体
### 账号
&nbsp;&nbsp;&nbsp;&nbsp;
账号中只存储用户ID, 没有密码(采用统一身份验证)
### 课程
&nbsp;&nbsp;&nbsp;&nbsp;
教师可以创建课程, 课程包含一个或多个群组, 
其中一个被选定为管理组, 其中的成员拥有对课程及其他群组的管理权
### 群组
&nbsp;&nbsp;&nbsp;&nbsp;
群组是最主要的实体, 组内平等地包含多个成员, 
每个群组都属于一个课程, 群组不能在课程间转移
### 作业
&nbsp;&nbsp;&nbsp;&nbsp;
作业管理组的成员可以发布作业, 作业可以有多个条目, 
发布者必须为作业设置:一个目的组, 一个完成时间段, 一个批改组. 
完成后, 批改组需对作业进行批改. 批改后, 产生分数值. 
### 作业条目
&nbsp;&nbsp;&nbsp;&nbsp;
每个作业条目包含一道题. 
### 题目
&nbsp;&nbsp;&nbsp;&nbsp;
题目独立于作业, 可以独立发布, 独立更新, 每个题目被指定一个标准答案. 
标准答案可以被更改, 但题目不行. 
### 答案
&nbsp;&nbsp;&nbsp;&nbsp;
答案是某人(学生或老师)对题目的解答, 答案可以重复发布, 不覆盖. 
### 分数
&nbsp;&nbsp;&nbsp;&nbsp;
分数实体内包含四个字段: 分数值, 用户, 用户组, 标签. 
其中, 用户组必须是用户所属的用户组中的一个. 若该分数值来源于某作业, 
则分数值为批改结果, 用户为答案提供者, 用户组为该作业面向的用户组, 
标签为{$作业名}_{$作业序号}
### 分数集
&nbsp;&nbsp;&nbsp;&nbsp;
取名不太恰当. 将分数集应用到一个学生时可得到总分. 分数集内应包含一个表达式, 
还应记录该分数集面向的课程id. 
### 总分
&nbsp;&nbsp;&nbsp;&nbsp;
总分总是由分数集产生. 总分对象内除了记录分数, 还记录着它由何种方法计算. 
### 聊天记录
&nbsp;&nbsp;&nbsp;&nbsp;
聊天记录包含的项: id, 发布者, 目标, 目标组, 发布时间, 聊天内容
### 公告
&nbsp;&nbsp;&nbsp;&nbsp;
略
