package top.natsuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import top.natsuki.constants.SystemConstants;
import top.natsuki.domain.ResponseResult;
import top.natsuki.domain.entity.Article;
import top.natsuki.domain.entity.Category;
import top.natsuki.domain.vo.ArticleListVO;
import top.natsuki.domain.vo.HotArticleVO;
import top.natsuki.domain.vo.PageVO;
import top.natsuki.mapper.CategoryMapper;
import top.natsuki.service.ArticleService;
import top.natsuki.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import top.natsuki.service.CategoryService;
import top.natsuki.utils.BeanCopyUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author nat4u
* @description 针对表【na_article】的数据库操作Service实现
* @createDate 2025-11-30 20:21:03
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只查询10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();

//        List<HotArticleVO> hotArticleVOS = new ArrayList<>();
//
//        for(Article article : articleList){
//            HotArticleVO hotArticleVO = new HotArticleVO();
//            BeanUtils.copyProperties(article,hotArticleVO);
//            hotArticleVOS.add(hotArticleVO);
//        }
        List<HotArticleVO> hotArticleVOS = BeanCopyUtils.copyBeanList(articleList, HotArticleVO.class);
        return ResponseResult.okResult(hotArticleVOS);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 如果有CategoryId 查询时候需要加入条件
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        // 已发布的文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 置顶的文章优先展示 根据isTop进行排序
        queryWrapper.orderByDesc(Article::getIsTop);

        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<Article> articleList = page.getRecords();


        // 查询categoryName

        // 先提取，在查询（一次），后拼接
        // 1. 提取所有 categoryId (利用 Set 去重)
        Set<Long> categoryIds = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        if (!categoryIds.isEmpty()) {
            // 这行代码只执行一次 SQL: SELECT * FROM na_category WHERE id IN (1, 2, ...)
            List<Category> categories = categoryMapper.selectList(
                    new LambdaQueryWrapper<Category>()
                            .in(Category::getId, categoryIds)
            );

            // 3. 将 List 转为 Map<id, name>，方便后续 O(1) 查找
            Map<Long, String> categoryMap = categories.stream()
                    .collect(Collectors.toMap(Category::getId, Category::getName));

            // 4. 遍历文章，从 Map 中取值并赋值
            for (Article article : articleList) {
                // 如果 Map 里有这个 ID，就设置名字，否则设为默认值或者不做处理
                if (categoryMap.containsKey(article.getCategoryId())) {
                    article.setCategoryName(categoryMap.get(article.getCategoryId()));
                }
            }
        }

        // stream流
//        articleList.stream()
//                .map(article ->article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());

        // 使用for内查询
//        for( Article article : articleList){
//            Category category =  categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }

        List<ArticleListVO> articleListVO = BeanCopyUtils.copyBeanList(articleList, ArticleListVO.class);

        PageVO pageVO = new PageVO(articleListVO, page.getTotal());
        return ResponseResult.okResult(pageVO);
    }
}






