<template>
  <div class="home-layout">
    <main class="main-content">
      <section class="hero">
        <h1>👋 欢迎来到我的数字花园</h1>
        <p>正在从后端获取真实数据...</p>
      </section>

      <div class="temp-placeholder">
        普通文章列表区域 (占据 70% 宽度)
      </div>
    </main>

    <aside class="sidebar">
      <HotArticles />
    </aside>
  </div>
</template>

<script setup lang="ts">
import HotArticles from "../components/HotArticles.vuue.vue";

</script>

<style scoped>
/* 使用 Flex 布局实现两栏结构 */
.home-layout {
  display: flex;
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
  gap: 2rem; /* 栏间距 */
}

.main-content {
  flex: 1; /* 占据剩余空间 */
  min-width: 0; /* 防止内容过宽撑破布局 */
}

.sidebar {
  width: 300px; /* 固定侧边栏宽度 */
  flex-shrink: 0;
}

/* 响应式：手机端变成单栏 */
@media (max-width: 768px) {
  .home-layout {
    flex-direction: column;
  }
  .sidebar {
    width: 100%;
  }
}

/* 之前的样式可以保留... */
.hero {
  background: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  margin-bottom: 2rem;
}
</style>