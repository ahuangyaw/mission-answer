# 使命必答（mission-answer）

一款基于 Vue 3 + Spring Boot + Redis + ChatGLM AI + RxJava + SSE 的 AI 答题应用平台。

## 一、需求分析

按照模块梳理功能：

- 用户模块

  - 注册
  - 登录
  - 管理用户 - 增删改查（仅管理员可用）

- 应用模块

  - 创建应用
  - 修改应用
  - 删除应用
  - 查看应用列表
  - 查看应用详情
  - 查看自己创建的应用
  - 管理应用 - 增删改查（仅管理员可用）
  - 审核发布和下架应用（仅管理员可用）
  - 应用分享（扫码查看）

- 题目模块

  - 创建题目（包括题目选项得分设置）
  - 修改题目
  - 删除题目
  - 管理题目 - 增删改查（仅管理员可用）
  - AI 生成题目

- 评分模块

  - 创建评分结果
  - 修改评分结果
  - 删除评分结果
  - 根据回答计算评分结果（多种评分策略）

  - 自定义规则评分 - 测评类
  - 自定义规则评分 - 打分类
  - AI 评分
  - 管理评分结果 - 增删改查（仅管理员可用）

  

- 回答模块

  - 提交回答（创建）
  - 查看某次回答的评分结果
  - 查看自己提交的回答列表
  - 管理回答 - 增删改查（仅管理员可用）

- 统计分析模块

  - 应用评分结果分析和查看



## 二、技术选型

### 前端：[mission-answer/mission-answer-frontend at master · ahuangyaw/mission-answer (github.com)](https://github.com/ahuangyaw/mission-answer/tree/master/mission-answer-frontend)

#### Web 网页开发

- Vue 3 
- Vue-CLI 脚手架
- Pinia 状态管理
- Axios 请求库
- Arco Design 组件库
- 前端工程化：ESLint + Prettier + TypeScript
- 富文本编辑器
- QRCode.js 二维码生成
- ⭐️ OpenAPI 前端代码生成



#### 小程序开发：[mission-answer/mbti-test-mini at master · ahuangyaw/mission-answer (github.com)](https://github.com/ahuangyaw/mission-answer/tree/master/mbti-test-mini)

- React
- Taro 跨端开发框架
- Taro UI 组件库

### 后端：[mission-answer/mission-answer-backend at master · ahuangyaw/mission-answer (github.com)](https://github.com/ahuangyaw/mission-answer/tree/master/mission-answer-backend)



- Java Spring Boot 开发框架（万用后端模板）
- 存储层：MySQL 数据库 + Redis 缓存 + 腾讯云 COS 对象存储
- MyBatis-Plus 及 MyBatis X 自动生成
- Redission 分布式锁
- Caffeine 本地缓存
- ⭐️ 基于 ChatGLM 大模型实现 AI 能力
- ⭐️ RxJava 响应式框架 + 多线程 / 线程池实战 
- ⭐️ Shardingsphere 分库分表 + 分布式 ID 雪花算法
- ⭐️ SSE 服务端推送
- ⭐️ 多种设计模式
- ⭐️ 多角度项目优化：性能、稳定性、幂等性优化等

