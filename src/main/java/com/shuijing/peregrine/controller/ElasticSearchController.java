package com.shuijing.peregrine.controller;

import com.shuijing.peregrine.common.base.Result;
import com.shuijing.peregrine.entity.es.Article;
import com.shuijing.peregrine.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2021-11-26
 */
@Api
@RestController
@RequestMapping("/elastic")
public class ElasticSearchController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping
    @ApiOperation("搜索")
    public Result<Page<Article>> search(String keyWord) {

        Pageable pageable = PageRequest.of(0, 10);
        Article article = new Article();

        article.setId(1L).setContent(keyWord).setTitle(keyWord);

        Page<Article> page = articleRepository.searchSimilar(article, new String[] { "title","content" }, pageable);
//        Page<Article> page = articleRepository.findByTitleLike(keyWord, pageable);
//        Iterable<Article> all = articleRepository.findAll();
        return Result.success(page);
    }


    @PostMapping
    @ApiOperation("新增")
    public Result<Iterable<Article>> create(@RequestBody List<Article> articleList) {
        return Result.success(elasticsearchRestTemplate.save(articleList));
    }

    @PutMapping
    @ApiOperation("更新")
    public Result<UpdateResponse> update(@RequestParam String id,
                                         @RequestParam String title,
                                         @RequestParam String content) {

        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("content", content);

        Document document = Document.from(params);

        UpdateQuery updateQuery = UpdateQuery.builder(id)
                        .withDocument(document)
                        .build();

        return Result.success(elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("article")));
    }


    @DeleteMapping
    @ApiOperation("删除")
    public Result<String> delete(@RequestParam String id) {
        return Result.success(elasticsearchRestTemplate.delete(id, Article.class));
    }

//    @GetMapping
//    @ApiOperation("搜索")
//    public Result<SearchHits<Article>> search(String keyWord) {
//
//        Pageable pageable = PageRequest.of(0,10);  // page 从第 0 页开始
//
//        HighlightBuilder.Field highlightField = new HighlightBuilder.Field("title")
//                        .preTags("<span>")
//                        .postTags("</span>");
//
//        Query query = new NativeSearchQueryBuilder()
//                        .withQuery(QueryBuilders.multiMatchQuery(keyWord, "author", "title", "content"))
//                        .withHighlightFields(highlightField)
//                        .withPageable(pageable)
//                        .build();
//        SearchHits<Article> search = elasticsearchRestTemplate.search(query, Article.class);
//        return Result.success(search);
//    }

    @GetMapping("/count")
    @ApiOperation("统计数量")
    public Result<Long> count(String keyWord) {// page 从第 0 页开始

        Query query = new NativeSearchQueryBuilder()
                        .withQuery(QueryBuilders.multiMatchQuery(keyWord, "author","title","content"))
                        .build();

        return Result.success(elasticsearchRestTemplate.count(query, Article.class));
    }

}
