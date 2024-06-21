package com.typemoon.service;

import com.typemoon.model.dto.AboutDTO;
import com.typemoon.model.dto.AuroraAdminInfoDTO;
import com.typemoon.model.dto.AuroraHomeInfoDTO;
import com.typemoon.model.dto.WebsiteConfigDTO;
import com.typemoon.model.vo.AboutVO;
import com.typemoon.model.vo.WebsiteConfigVO;

public interface AuroraInfoService {

    void report();

    AuroraHomeInfoDTO getAuroraHomeInfo();

    AuroraAdminInfoDTO getAuroraAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}
