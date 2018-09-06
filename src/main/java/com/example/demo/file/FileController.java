package com.example.demo.file;

import com.example.demo.excel.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/9/4
 */
@Controller
@RequestMapping("/fileTest")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/async")
    public ResponseEntity getFile(@RequestPart("file")MultipartFile file){
        ModelAndView mav = new ModelAndView();
        int insert = fileService.insert(file);
        mav.addObject(insert);
        return ResponseEntity.ok("");
    }

    @PostMapping("/async02")
    public ModelAndView async02(@RequestParam("file")MultipartFile file){
        ModelAndView mav = new ModelAndView();
        int insert = fileService.insert(file);
        mav.addObject(insert);
        return mav;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }


}
