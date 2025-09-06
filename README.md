# 风起示例项目 (Fengqi Example)

这是一个基于Spring Boot的示例项目，展示了基本的企业级应用开发框架和实践。

## 项目结构

```
fengqi-example/
├── fengqi-business/     # 业务模块，包含主要功能实现
├── fengqi-common/       # 公共模块，包含共享组件和工具类
└── pom.xml              # 父项目依赖管理
```

## 技术栈

- **后端框架**: Spring Boot 3.2.10
- **构建工具**: Maven
- **数据库**: MySQL
- **ORM框架**: MyBatis-Plus
- **日志**: SLF4J + Log4j2
- **API文档**: 支持RESTful API
- **国际化**: 支持多语言配置

## 功能特点

- 用户管理 (增删改查)
- 数据初始化
- 国际化错误处理
- 数据库连接池管理
- 日志记录

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 5.7+

### 配置说明

主要配置文件位于 `fengqi-business/src/main/resources/application.yml`，包含以下关键配置：
- 服务器端口: 8080
- 上下文路径: /demo
- 数据库连接信息
- MyBatis配置

### 构建项目

在项目根目录执行以下命令：

```bash
mvn clean package -DskipTests
```

### 运行项目

在 `fengqi-business` 目录下执行以下命令：

```bash
java -jar target/fengqi-business-1.0-SNAPSHOT.jar
```

## API接口示例

项目启动后，可以通过以下接口访问功能：

### 用户管理

- **获取用户列表**
  ```
  GET http://localhost:8080/demo/api/user/list
  ```

- **根据ID获取用户详情**
  ```
  GET http://localhost:8080/demo/api/user/{id}
  ```
  *参数:* 
  - `id`: 用户ID
  - `lang`: 可选，语言参数 (en/zh_CN)

## 开发说明

1. 克隆项目
2. 导入到IDE（如IntelliJ IDEA、Eclipse等）
3. 配置数据库连接
4. 运行 `BusinessApplication` 主类

## 注意事项

- 确保MySQL服务已启动
- 默认会自动创建必要的表结构和测试数据
- 开发环境请勿使用生产配置