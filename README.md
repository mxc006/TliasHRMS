# Tlias HRMS 后端服务

基于 Spring Boot 的企业级人力资源管理系统后端接口服务。

---

## 技术栈

- Spring Boot 2.7.x
- JWT (jjwt)
- MyBatis + PageHelper
- MySQL 8.0
- AOP 操作日志
- 阿里云 OSS
- Maven 3.6+
- Docker Compose

---

## 功能模块

- 登录认证：JWT 生成 Token，拦截器校验
- 部门管理：增删改查、分页
- 员工管理：增删改查、条件分页、批量删除
- 操作日志：AOP 自动记录
- 文件上传：阿里云 OSS 头像上传
- 数据看板：员工/部门统计

---

## 项目结构

```
src/main/java/com/example/tlias/
├── controller/      # 控制器
├── service/         # 业务逻辑
├── mapper/          # MyBatis 映射
├── pojo/            # 实体/DTO/VO
├── utils/           # JWT、OSS、响应工具
├── annotation/      # 自定义注解
├── aspect/          # AOP 切面
├── config/          # 配置类
└── interceptor/     # 登录拦截器
```

---

## 快速开始

### 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 8.0

### 1. 克隆项目
```bash
git clone https://github.com/mxc006/TliasHRMS.git
cd TliasHRMS
```

### 2. 修改数据库配置
编辑 `src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tlias?useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

> 请勿硬编码 OSS 密钥，建议使用环境变量。

### 3. 导入 SQL
将项目中的 `tlias.sql` 导入 MySQL。

### 4. 启动
```bash
mvn clean install
mvn spring-boot:run
```

访问 `http://localhost:8080`

### 5. Docker 部署（可选）
```bash
docker-compose up -d
```

---

## API 接口预览

| 接口 | 方法 | 说明 |
|------|------|------|
| `/login` | POST | 登录 |
| `/depts` | GET/POST/PUT/DELETE | 部门管理 |
| `/emps` | GET/POST/PUT/DELETE | 员工管理 |
| `/emps/{ids}` | DELETE | 批量删除 |
| `/upload` | POST | 上传头像 |

> 如集成 Swagger，访问 `/doc.html` 查看完整文档。

---

## 前端仓库

👉 [vue-tlias-management](https://github.com/mxc006/vue-tlias-management)

---

## 作者

[mxc006](https://github.com/mxc006)

## 许可证

MIT