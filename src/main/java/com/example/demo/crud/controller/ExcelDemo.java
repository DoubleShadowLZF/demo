package com.example.demo.crud.controller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.jxls.reader.*;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDemo {
    @Test
    public void excelTest() throws IOException, SAXException, InvalidFormatException {
        String configFild = File.separator + "file" + File.separator + "empConfig.xml";

        InputStream inputXML = new BufferedInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(configFild));
        XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
String userDir = System.getProperty("user.dir");
        String sotrePath = userDir + File.separator + "filesystem" + File.separator + "downloaded_excel.xls";
        InputStream inputXLS = new BufferedInputStream(new FileInputStream(new File(sotrePath)));
        Map beans = new HashMap<>();
        List emps = new ArrayList();
        beans.put("emps",emps);

        ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes(true);
        ReaderConfig.createConvertUtilsBean(true);

        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        if(readStatus.isStatusOK()){
            System.out.println("jxls读取Excel成功！");
            System.out.printf("beans size: %s,\t%s\n",emps.size(),emps);
        }
    }
}
