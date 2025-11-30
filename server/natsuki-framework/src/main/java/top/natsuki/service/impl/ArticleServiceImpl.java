package top.natsuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.natsuki.domain.ResponseResult;
import top.natsuki.domain.entity.Article;
import top.natsuki.service.ArticleService;
import top.natsuki.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author nat4u
* @description 针对表【na_article】的数据库操作Service实现
* @createDate 2025-11-30 20:21:03
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, "0");
        // 按浏览量进行排序
        queryWrapper.orderByAsc(Article::getViewCount);
        // 最多只查询10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();
        return ResponseResult.okResult(articleList);
    }
}




