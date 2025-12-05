package top.natsuki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.natsuki.domain.ResponseResult;
import top.natsuki.domain.entity.Article;
import top.natsuki.service.ArticleService;

import java.util.List;

/**
 * @author nat4u
 * @date 2025/11/30  16:18
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return  articleService.articleList(pageNum, pageSize, categoryId);
    }
}
