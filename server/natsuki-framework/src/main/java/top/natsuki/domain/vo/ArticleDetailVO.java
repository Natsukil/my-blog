package top.natsuki.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author nat4u
 * @date 2025/12/5  15:48
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVO {
    /**
     * 文章ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章分类ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 文章缩略图
     */
    private String thumbnail;
    /**
     * 文章状态（0已发布，1代表草稿）
     */
    private Integer status;

    /**
     * 浏览量
     */
    private Long viewCount;

    /**
     * 是否允许评论（0否，1是）
     */
    private Integer isComment;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志（ 0 代表存在 1 代表删除）
     */

}
