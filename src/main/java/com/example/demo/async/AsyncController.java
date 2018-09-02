package com.example.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author Double
 */
@Slf4j
@EnableAsync
@RestController
@RequestMapping("/asyncTest")
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @GetMapping("/test")
    public ResponseEntity test(){
        return asyncService.test();
    }

    @GetMapping("/test01")
    public ResponseEntity test01(){
        asyncService.test01();
        return ResponseEntity.ok("SUCCESS");
    }
    @GetMapping("/test02")
    public ResponseEntity test02(){
        return ResponseEntity.ok(asyncService.test01());
    }

    @GetMapping("/testSample")
    public ResponseEntity testSample(){
        for (int i = 0; i < 10; i++) {
            asyncService.test01();
            asyncService.test02();
            asyncService.test03();
        }
        return ResponseEntity.ok("");
    }

    @GetMapping("/annotation")
    public ResponseEntity testAnnotation(){
        return asyncService.annocation();
    }

    @GetMapping("/future")
    public ResponseEntity testAnnotationFuture() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(asyncService.annocationFuture().get());
    }

    @GetMapping("/customReturnFuture")
    public ResponseEntity testCustomReturnFuture() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(asyncService.customReturnFutrue().get());
    }



}
