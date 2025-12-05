package top.natsuki.service;

import top.natsuki.domain.ResponseResult;
import top.natsuki.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author nat4u
* @description 针对表【na_catagory(分类表：存储层级分类信息，用于文章/产品归类等)】的数据库操作Service
* @createDate 2025-12-01 14:04:50
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
