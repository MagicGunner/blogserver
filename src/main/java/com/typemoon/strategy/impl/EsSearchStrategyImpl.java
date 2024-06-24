//package com.typemoon.strategy.impl;//package com.typemoon.strategy.impl;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
//import co.elastic.clients.elasticsearch._types.query_dsl.Query;
//import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
//import co.elastic.clients.elasticsearch.core.SearchRequest;
//import co.elastic.clients.elasticsearch.core.SearchResponse;
//import co.elastic.clients.elasticsearch.core.search.Highlight;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import com.typemoon.model.dto.ArticleSearchDTO;
//import com.typemoon.strategy.SearchStrategy;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import lombok.extern.log4j.Log4j2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Log4j2
//@Service("esSearchStrategyImpl")
//public class EsSearchStrategyImpl implements SearchStrategy {
//
//    @Autowired
//    private ElasticsearchClient elasticsearchClient;
//
//    @Override
//    public List<ArticleSearchDTO> searchArticle(String keywords) throws IOException {
//        if (StringUtils.isBlank(keywords)) {
//            return new ArrayList<>();
//        }
//        return search(buildQuery(keywords));
//    }
//
//    private Query buildQuery(String keywords) {
//        BoolQuery.Builder boolQueryBuilder = QueryBuilders.bool();
//        boolQueryBuilder.must(
//                QueryBuilders.match().field("articleTitle").query(keywords).build()._toQuery()
//        );
//        boolQueryBuilder.must(
//                QueryBuilders.match().field("articleContent").query(keywords).build()._toQuery()
//        );
//        boolQueryBuilder.must(
//                QueryBuilders.term().field("isDelete").value(false).build()._toQuery()
//        );
//        boolQueryBuilder.must(
//                QueryBuilders.term().field("status").value("PUBLIC").build()._toQuery()
//        );
//
//        return boolQueryBuilder.build()._toQuery();
//    }
//
//
//    private List<ArticleSearchDTO> search(Query query) throws IOException {
//        Highlight highlight = Highlight.of(h -> h.fields("articleTitle", f -> f.preTags("<em>").postTags("</em>"))
//                .fields("articleContent", f -> f.preTags("<em>").postTags("</em>").fragmentSize(50)));
//
//        SearchRequest searchRequest = SearchRequest.of(s -> s
//                .index("your_index_name")
//                .query(query)
//                .highlight(highlight)
//        );
//
//        SearchResponse<ArticleSearchDTO> response = elasticsearchClient.search(searchRequest, ArticleSearchDTO.class);
//
//        return response.hits().hits().stream().map(hit -> {
//            ArticleSearchDTO article = hit.source();
//            if (article != null) {
//                article.setArticleTitle(getHighlight(hit, "articleTitle"));
//                article.setArticleContent(getHighlight(hit, "articleContent"));
//            }
//            return article;
//        }).collect(Collectors.toList());
//    }
//
//    private String getHighlight(Hit<ArticleSearchDTO> hit, String field) {
//        if (hit.highlight() != null && hit.highlight().containsKey(field)) {
//            return String.join(" ", hit.highlight().get(field));
//        }
//        return null;
//    }
//
//}