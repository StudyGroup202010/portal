package com.portal.b.industry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param model Modelクラス
     * @return 画面のテンプレート名
     */
 //   @CrossOrigin
    @GetMapping("/api/industryList")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Industry> getIndustryList(@RequestParam(value = "name", defaultValue = "World") String name) {
        // 産業分類マスタ一覧の生成
        return industryService.selectMany();
    }
}