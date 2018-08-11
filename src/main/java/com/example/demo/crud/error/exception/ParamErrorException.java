package com.example.demo.crud.error.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description 解決密碼錯誤抛出的異常信息
 * @since 2018/8/11
 */
@Slf4j
public class ParamErrorException extends RuntimeException {
    public ParamErrorException() {
        super("密碼錯誤。。。");
    }

}
