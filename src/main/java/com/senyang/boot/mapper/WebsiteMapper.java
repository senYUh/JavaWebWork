package com.senyang.boot.mapper;


import com.senyang.boot.entity.WebsiteCategory;
import com.senyang.boot.entity.WebsiteShow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsiteMapper {
    int insertCategory(WebsiteCategory category);
    int insertWebsite(WebsiteShow websiteShow);
    int deleteCategory(Integer categoryId);
    int deleteWebsite(Integer websiteId);
    int deleteAllByType(Integer websiteType);
    int updateWeb(WebsiteShow websiteShow);
    List<WebsiteCategory> getAllWebsiteCategory();
    List<WebsiteShow> getWebsiteByType(Integer websiteType);
    List<WebsiteShow> getAllWebsite();

}
