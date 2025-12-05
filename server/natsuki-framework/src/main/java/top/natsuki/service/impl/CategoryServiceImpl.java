package top.natsuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.natsuki.constants.SystemConstants;
import top.natsuki.domain.ResponseResult;
import top.natsuki.domain.entity.Article;
import top.natsuki.domain.entity.Category;
import top.natsuki.domain.vo.CategoryVO;
import top.natsuki.service.ArticleService;
import top.natsuki.service.CategoryService;
import top.natsuki.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import top.natsuki.utils.BeanCopyUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author nat4u
* @description 针对表【na_catagory(分类表：存储层级分类信息，用于文章/产品归类等)】的数据库操作Service实现
* @createDate 2025-12-01 14:04:50
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        //    从文章表中拿到有有效文章的分类id
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleQueryWrapper.select(Article::getCategoryId);

        List<Article> articleList = articleService.list(articleQueryWrapper);

        Set<Long> categoryIds = articleList.stream()
                // 1. 映射：把 Article 对象转成 Long 类型的 categoryId
                .map(Article::getCategoryId)
                // 2. 收集：转换成 Set 集合，自动去除重复的 ID
                .collect(Collectors.toSet());

        // 边界判断：如果没有任何符合条件的文章，直接返回空列表，防止后面 SQL 报错
        if (categoryIds.isEmpty()) {
            return ResponseResult.okResult(Collections.emptyList());
        }


        List<Category> categories = listByIds(categoryIds);
        //    然后去分类表中拿去对应的名称
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(String.valueOf(category.getStatus())))
                .collect(Collectors.toList());
        //    封装VO
        List<CategoryVO> categoryVOS = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);
        return ResponseResult.okResult(categoryVOS);
    }
}




