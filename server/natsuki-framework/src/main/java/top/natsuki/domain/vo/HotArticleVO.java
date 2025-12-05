package top.natsuki.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 热门文章展示对象
 * VO 只包含前端页面需要的字段
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVO implements Serializable {
    
    // 1. 文章ID (必须给，前端点击跳转详情用)
    private Long id;

    // 2. 标题
    private String title;


    // 4. 浏览量 (热门文章的核心指标)
    private Long viewCount;

    // ⚠️ 注意：这里绝对不要包含 delFlag, updateTime, createBy 等前端不需要的敏感字段
}