package com.example.demo.excel.conponent;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileBean {
    /**
     * 文件 id
     */
    private Long fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 上传时间
     */
    private Timestamp uploadTime;
    /**
     * 上传用户
     */
    private String uploadUser;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 备注
     */
    private String note ;
    /**
     * 存储路径
     */
    private String path ;
    /**
     * 是否删除
     */
    private short isDeleted;
    /**
     * 业务类型
     */
    private String busiType;
    /**
     * 业务对象
     */
    private String busiTarget ;
    /**
     * 拓展存储类型
     */
    private String storeType;
}
