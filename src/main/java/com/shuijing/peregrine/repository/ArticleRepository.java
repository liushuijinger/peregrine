package com.shuijing.peregrine.repository;

import com.shuijing.peregrine.entity.es.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021-11-26
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
//    User findByName(String name);

    Page<Article> findByTitleLike(String title, Pageable page);
}

