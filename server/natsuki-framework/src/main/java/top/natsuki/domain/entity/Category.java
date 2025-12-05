package top.natsuki.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @TableName na_category
 */
@TableName(value ="na_category")
@Data
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long pid;

    private String description;

    private Integer status;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;

    private Integer delFlag;

    @Serial
    private static final long serialVersionUID = 1L;
}