package top.natsuki.domain.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 热门文章展示对象
 * VO 只包含前端页面需要的字段
 */
@Data
public class HotArticleVO implements Serializable {
    
    // 1. 文章ID (必须给，前端点击跳转详情用)
    private Long id;

    // 2. 标题
    private String title;

    // 3. 摘要 (列表页不需要展示全文，只展示摘要，节省流量)
    private String summary;

    // 4. 浏览量 (热门文章的核心指标)
    private Long viewCount;

    // 5. 封面图 (Entity里可能是 relative path，VO里可以处理成 full url)
    private String coverUrl;

    // 6. 发布时间 (Entity里是 LocalDateTime，这里依然可以是 LocalDateTime，或者转成 "3小时前" 这种 String)
    private LocalDateTime createTime;

    // ⚠️ 注意：这里绝对不要包含 delFlag, updateTime, createBy 等前端不需要的敏感字段
}