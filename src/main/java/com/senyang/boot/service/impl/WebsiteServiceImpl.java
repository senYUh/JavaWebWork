package com.senyang.boot.service.impl;

import com.senyang.boot.entity.WebsiteCategory;
import com.senyang.boot.entity.WebsiteShow;
import com.senyang.boot.mapper.WebsiteMapper;
import com.senyang.boot.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    WebsiteMapper websiteMapper;

    @Override
    public int addCategory(WebsiteCategory category) {
        return websiteMapper.insertCategory(category);
    }

    @Override
    public int addWebsite(WebsiteShow websiteShow) {
        return websiteMapper.insertWebsite(websiteShow);
    }

    @Override
    public int rmCategory(Integer categoryId) {
        websiteMapper.deleteAllByType(categoryId);
        return websiteMapper.deleteCategory(categoryId);
    }

    @Override
    public int rmWebsite(Integer websiteId) {
        return websiteMapper.deleteWebsite(websiteId);
    }

    @Override
    public int updateWebsite(WebsiteShow websiteShow) {
        return websiteMapper.updateWeb(websiteShow);
    }

    @Override
    public List<WebsiteCategory> getAllWebsiteCategory() {
        return websiteMapper.getAllWebsiteCategory();
    }

    @Override
    public List<WebsiteShow> getWebsiteByType(Integer websiteType) {
        return websiteMapper.getWebsiteByType(websiteType);
    }

    @Override
    public List<WebsiteShow> getAllWebsite() {
        return websiteMapper.getAllWebsite();
    }
}
