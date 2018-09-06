package com.example.demo.excel.service.impl;

import com.example.demo.excel.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/9/4
 */
@Service("fileService")
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public int insert(Object obj) {
        try {
            save((MultipartFile) obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Async
    public void save(MultipartFile file) throws IOException {
        String udir = System.getProperty("user.dir");
        String filePath = new StringBuilder(udir).append(File.separator).append("file")
                    .toString();
        log.debug("文件的路径：{}",filePath);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File f = new File(filePath);
        if(!f.exists()){
            f.mkdirs();
            log.debug("创建路径{}",filePath);
        }
        filePath = new StringBuilder(filePath).append(File.separator)
                .append(file.getOriginalFilename()).append(System.currentTimeMillis())
                .toString();
        f = new File(filePath);
        if(!f.exists()){
            f.createNewFile();
            log.debug("创建文件：{}",filePath);
        }
        file.getInputStream();
        file.transferTo(f);
        log.debug("文件保存成功");
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int query(Long id) {
        return 0;
    }
}
