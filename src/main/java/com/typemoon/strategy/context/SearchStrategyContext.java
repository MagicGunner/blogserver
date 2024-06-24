package com.typemoon.strategy.context;//package com.typemoon.strategy.context;

import com.typemoon.model.dto.ArticleSearchDTO;
import com.typemoon.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.typemoon.enums.SearchModeEnum.getStrategy;

@Service
public class SearchStrategyContext {

    @Value("${search.mode}")
    private String searchMode;

    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;

    public List<ArticleSearchDTO> executeSearchStrategy(String keywords) throws IOException {
        return searchStrategyMap.get(getStrategy(searchMode)).searchArticle(keywords);
    }

}
