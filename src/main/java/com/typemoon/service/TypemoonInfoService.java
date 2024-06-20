package com.typemoon.service;

import com.typemoon.model.dto.AboutDTO;
import com.typemoon.model.dto.TypemoonAdminInfoDTO;
import com.typemoon.model.dto.TypemoonHomeInfoDTO;
import com.typemoon.model.dto.WebsiteConfigDTO;
import com.typemoon.model.vo.AboutVO;
import com.typemoon.model.vo.WebsiteConfigVO;

public interface TypemoonInfoService {

    void report();

    TypemoonHomeInfoDTO getTypemoonHomeInfo();

    TypemoonAdminInfoDTO getTypemoonAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}
