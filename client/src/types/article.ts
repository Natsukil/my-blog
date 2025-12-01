// src/types/article.ts

// 1. 定义单篇文章的结构
// 根据你提供的 JSON，把所有字段都定义出来，不确定的可以用 null 联合类型
export interface Article {
    id: number;
    title: string;
    // summary: string | null;
    // content: string;
    viewCount: number;      // 热门文章通常看这个
    // isTop: number;          // 1 代表置顶，可以用作特殊的 UI 标记
    // thumbnail: string | null;
    // createTime: string;
    // categoryId: number;
    // ... 其他字段根据需要添加，暂时这些够用了
}

// 2. 定义通用的 API 响应结构
// 这是一个泛型接口 (Generic Interface)，<T> 代表 data 的类型是可变的
export interface ApiResponse<T> {
    code: number;
    msg: string;
    data: T;
}