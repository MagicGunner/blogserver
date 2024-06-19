package com.typemoon.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypemoonHomeInfoDTO {
    private Integer articleCount;

    private Integer talkCount;

    private Integer categoryCount;

    private Integer tagCount;

    private WebsiteConfigDTO websiteConfigDTO;

    private Integer viewCount;
}

