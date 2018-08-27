package com.example.demo;

import com.example.demo.excel.Dao.ExcelDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTests {
    @Autowired
    private ExcelDao excelDao;

    @Test
    public void testList(){
        List list = excelDao.list();
System.out.println(list);
    }


}

