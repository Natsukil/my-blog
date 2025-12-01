import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
    plugins: [vue()],
    server: {
        proxy: {
            '/api': {
                target: 'http://localhost:7777', // 2. 转发给后端目标地址
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '') // 3. 去掉 /api 前缀再发给后端
            }
        }
    }
})
