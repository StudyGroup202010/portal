package com.portal.b.industry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.b.common.domain.model.Industry;
import com.portal.b.industry.domain.service.IndustryService;

/**
 * 産業分類マスタのController
 * 
 */
@RestController
public class industryRestController {

    @Autowired
    private IndustryService industryService;

    /**
     * 産業分類マスタのGET用メソッド.
     * 
     * @return industryService.selectMany()
     */
    @GetMapping("/api/industryList")
    public List<Industry> getIndustryList() {
        // 産業分類マスタ一覧を返信
        return industryService.selectMany();
    }
}