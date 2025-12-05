package top.natsuki.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVO {
    private Long id;

    private String title;

    private String summary;

    private String thumbnail;

    private String categoryName;

    private Long viewCount;

    private LocalDateTime createTime;


}
