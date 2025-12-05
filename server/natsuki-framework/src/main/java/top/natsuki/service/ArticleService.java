package top.natsuki.service;

import top.natsuki.domain.ResponseResult;
import top.natsuki.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author nat4u
* @description 针对表【na_article】的数据库操作Service
* @createDate 2025-11-30 20:21:03
*/
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}
