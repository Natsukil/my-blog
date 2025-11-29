<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// 1. 定义一个响应式变量，用来存后端发来的数据
const message = ref('正在等待后端响应...');

// 2. 定义调用函数
const connectToBackend = async () => {
  try {
    // 发送 GET 请求给 Spring Boot
    const response = await axios.get('http://localhost:8080/test');

    // 3. 拿到数据，赋值给变量
    console.log('后端返回的数据:', response);
    message.value = response.data;

  } catch (error) {
    console.error('连接失败:', error);
    message.value = '❌ 连接失败，请检查后端是否启动，或者控制台是否有跨域报错。';
  }
};

// 3. 页面一加载就自动调用
onMounted(() => {
  connectToBackend();
});
</script>

<template>
  <div style="text-align: center; margin-top: 50px;">
    <h1>前后端连通性测试</h1>
    <h2 style="color: #42b883;">{{ message }}</h2>
  </div>
</template>

<style scoped>

</style>