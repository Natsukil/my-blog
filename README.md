# 个人博客

## 简介
这是我的个人博客项目，前后端分离：
- 前端（client）：Vite + Vue 3 + JavaScript
- 后端（server）：Spring Boot

## 技术栈
- 前端框架：[Vue 3 + JavaScript]
- 样式：[CSS]
- 构建工具：[Vite]
- 后端框架：[Spring Boot 4.0.0]
- 接口格式：[REST / JSON]

## 快速开始

### 前端（client）
```bash
cd client
npm install
npm run dev       # 本地开发（默认启动在 http://localhost:5173）
npm run build     # 构建生产版本（输出到 dist/）
```

### 后端（server）
```bash
cd server
./mvnw spring-boot:run     # 或使用 IDE 运行主类
# 默认接口示例：http://localhost:8080/api/...
```

### 联调
- 前端通过环境变量配置后端地址（示例：.env）
```
VITE_API_BASE_URL=http://localhost:8080/api
```

## 目录结构
```
my-blog/
├── client/         # 前端（Vue3 + Vite）
│   ├── src/        # 源代码
│   └── public/     # 静态资源
├── server/         # 后端（Spring Boot）
│   ├── src/        # Java 源代码
│   └── resources/  # 配置与静态资源
├── posts/          # 博客文章
└── README.md       # 项目说明
```

## 功能特性
- 📝 文章发布与管理（后端 API + 前端界面）
- 🏷️ 标签分类
- 🔍 文章搜索
- 📱 响应式设计
- 🌙 暗黑模式支持

## 部署
- 前端：构建后将 client/dist 部署到静态服务器或 CDN
- 后端：将 Spring Boot 打包为可运行 JAR 或部署到容器/云平台
- 生产环境将 VITE_API_BASE_URL 指向后端公网地址

## 许可证
MIT License
