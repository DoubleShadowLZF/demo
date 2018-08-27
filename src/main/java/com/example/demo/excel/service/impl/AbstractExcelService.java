package com.example.demo.excel.service.impl;

import com.example.demo.excel.Dao.ExcelDao;
import com.example.demo.excel.conponent.FileBean;
import com.example.demo.excel.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public abstract class AbstractExcelService implements FileService {

    @Autowired
    protected ExcelDao excelDao;

    @Override
    public int insert(Object obj) {

        return 0;
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
