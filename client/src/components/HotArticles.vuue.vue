<template>
  <div class="hot-section">
    <div class="section-header">
      <h3>🔥 热门文章</h3>
    </div>

    <div v-if="isLoading" class="loading-state">
      正在加载热门内容...
    </div>

    <div v-else-if="errorMsg" class="error-state">
      {{ errorMsg }}
    </div>

    <ul v-else class="article-list">
      <li v-for="(item, index) in hotList" :key="item.id" class="article-item">
        <span class="rank" :class="{ 'top-rank': index < 3 }">{{ index + 1 }}</span>

        <div class="info">
          <h4 class="title">
            {{ item.title }}
<!--            <span v-if="item.isTop === 1" class="badge-top">Top</span>-->
          </h4>
          <div class="meta">
            <span>👁️ {{ item.viewCount }} 阅读</span>
<!--            <span>📅 {{ item.createTime.split('T')[0] }}</span>-->
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import type { Article, ApiResponse } from '../types/article'; // 引入我们定义的类型

// 定义状态
const hotList = ref<Article[]>([]);
const isLoading = ref(true);
const errorMsg = ref('');

// 获取数据的函数
const fetchHotArticles = async () => {
  try {
    // 注意：这里我们用了 /api 前缀，这会触发 vite.config.ts 里的代理
    const response = await fetch('/api/article/hotArticleList');

    if (!response.ok) {
      throw new Error(`网络请求失败: ${response.status}`);
    }

    const resData: ApiResponse<Article[]> = await response.json();

    if (resData.code === 200) {
      // 成功拿到数据
      // 我们可以按 viewCount 排序，或者过滤掉已删除的 (delFlag === 1)
      hotList.value = resData.data;
    } else {
      errorMsg.value = resData.msg || '获取数据失败';
    }
  } catch (err) {
    errorMsg.value = '网络连接异常，请稍后重试';
    console.error(err);
  } finally {
    isLoading.value = false; // 无论成功失败，都结束加载状态
  }
};

// 组件挂载完成后立即触发请求
onMounted(() => {
  fetchHotArticles();
});
</script>


<style scoped>
.hot-section {
  background: #fff;
  border-radius: 8px;
  padding: 1.5rem;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.section-header h3 {
  margin-top: 0;
  border-bottom: 2px solid #f0f0f0;
  padding-bottom: 10px;
  font-size: 1.2rem;
}

.article-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.article-item {
  display: flex;
  align-items: flex-start; /* 对齐顶部 */
  padding: 12px 0;
  border-bottom: 1px dashed #eee;
}

.article-item:last-child {
  border-bottom: none;
}

.rank {
  font-size: 1.2rem;
  font-weight: bold;
  color: #999;
  margin-right: 15px;
  width: 20px;
  text-align: center;
  font-style: italic;
}

.top-rank {
  color: #ff6b6b; /* 前三名用红色高亮 */
}

.info {
  flex: 1;
}

.title {
  margin: 0 0 5px 0;
  font-size: 1rem;
  color: #333;
  line-height: 1.4;
  cursor: pointer;
}

.title:hover {
  color: #42b983;
}

.badge-top {
  background: #ff4757;
  color: white;
  font-size: 0.7rem;
  padding: 2px 6px;
  border-radius: 4px;
  margin-left: 5px;
  vertical-align: middle;
}

.meta {
  font-size: 0.8rem;
  color: #aaa;
  display: flex;
  gap: 15px;
}

.loading-state, .error-state {
  text-align: center;
  padding: 20px;
  color: #999;
}
.error-state {
  color: #ff6b6b;
}
</style>