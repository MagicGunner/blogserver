package com.typemoon.strategy;

import com.typemoon.model.dto.ArticleSearchDTO;

import java.io.IOException;
import java.util.List;

public interface SearchStrategy {

    List<ArticleSearchDTO> searchArticle(String keywords) throws IOException;

}
