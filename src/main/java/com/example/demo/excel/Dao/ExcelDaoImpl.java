package com.example.demo.excel.Dao;

import com.example.demo.excel.conponent.FileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("excelDao")
public class ExcelDaoImpl implements ExcelDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Override
    public List list() {
        BeanPropertyRowMapper<FileBean> mapper = new BeanPropertyRowMapper<>(FileBean.class);
        return jdbcTemplate.query("SELECT * FROM file", mapper);
    }
}
