package com.example.demo.excel.controller;

import com.example.demo.excel.service.impl.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Double
 */
@Controller
@RequestMapping("/excelTest")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    /**
     * @Description 获取Excel模板
     * @param [req, resp]
     * @return org.springframework.web.servlet.ModelAndView
     * @Data 2018/8/26 20:46
     * @author Double
     */
    @GetMapping("/getTemplate")
    public ModelAndView getTemplate(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        String path = "upload_excel.xls";
        excelService.loadFileItem(resp, path);
        return null;
    }

    @GetMapping("/getEmps")
    public ModelAndView getExcel(HttpServletResponse resp) throws IOException {
        String path = "download_excel.xls";
        excelService.loadFile(resp,path);
        return null;
    }



}
