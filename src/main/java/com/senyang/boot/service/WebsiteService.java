package com.senyang.boot.service;

import com.senyang.boot.entity.WebsiteCategory;
import com.senyang.boot.entity.WebsiteShow;

import java.util.List;

public interface WebsiteService{
    int addCategory(WebsiteCategory category);
    int addWebsite(WebsiteShow websiteShow);
    int rmCategory(Integer categoryId);
    int rmWebsite(Integer websiteId);
    int updateWebsite(WebsiteShow websiteShow);
    List<WebsiteCategory> getAllWebsiteCategory();
    List<WebsiteShow> getWebsiteByType(Integer websiteType);
    List<WebsiteShow> getAllWebsite();
}
