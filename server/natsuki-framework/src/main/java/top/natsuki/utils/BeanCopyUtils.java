package top.natsuki.utils;

import org.springframework.beans.BeanUtils;
import top.natsuki.domain.entity.Article;
import top.natsuki.domain.vo.HotArticleVO;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    private BeanCopyUtils(){

    }

    public static <V> V copyBean(Object source, Class<V> clazz) {

        V result = null;
        try {
//            创建目标对象
            result = clazz.newInstance();
//            实现属性拷贝
            BeanUtils.copyProperties(source, result);
//            返回结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <V> List<V> copyBeanList(List<? extends Object> list, Class<V> clazz) {
        return list.stream()
                .map(item-> copyBean(item, clazz))
                .collect(Collectors.toList());
    }
    public static void main() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("测试标题");
        Article article2 = new Article();
        article2.setId(2L);
        article2.setTitle("测试标题2");

        HotArticleVO hotArticleVO = copyBean(article, HotArticleVO.class);
        System.out.println(hotArticleVO);
    }
}
