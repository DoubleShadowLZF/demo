package com.example.demo.excel.service.impl;

import com.example.demo.crud.dao.EmployeeDao;
import com.example.demo.crud.entities.Employee;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import sun.nio.ch.FileChannelImpl;

import javax.annotation.processing.FilerException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelService extends AbstractExcelService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment ;

    @Autowired
    private EmployeeDao employeeDbDao ;


    /**
     * @Description 加载excel模板
     * @param [resp, path]
     * @return org.apache.tomcat.util.http.fileupload.FileItem
     * @Data 2018/8/26 18:11
     * @author Double
     */
    public void loadFileItem(HttpServletResponse resp , String excelFile) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(excelFile,"UTF-8"));

        ServletOutputStream os = resp.getOutputStream() ;

        fileOsByPath(excelFile, os);
    }

    /**
     * @Description 将路径下文件填充到 OutputStream 中
     * @param [excelFile, os]
     * @return void
     * @Data 2018/8/26 22:00
     * @author Double
     */
    private void fileOsByPath(String excelFile, ServletOutputStream os) {
        excelFile = File.separator+"file"+ File.separator+excelFile;
log.debug("the excelFile is {}",excelFile);
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelFile);
        try{
            byte[] buf = new byte[1024];
            int bytesRead = 0 ;
            while((bytesRead = in.read(buf)) > 0){
                os.write(buf,0,bytesRead);
            }
        }catch (Exception e){
            log.error("文件读取出错:"+e);
        }finally {
            try {
                os.close();
                in.close();
            } catch (IOException e) {
                log.error(String.valueOf(e));
            }
        }
    }

    /**
     * @Description 上传文件,并填充数据
     * @param [resp, excelFile]
     * @return void
     * @Data 2018/8/26 22:14
     * @author Double
     */
    public void loadFile(HttpServletResponse resp,String fileName) throws IOException {
        String excelFile = null;
        excelFile = new StringBuilder().append(File.separator).append("file").append(File.separator).append(fileName).toString();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelFile);
        List<Employee> emps = employeeDbDao.list();
        ServletOutputStream os = resp.getOutputStream();
String userDir = System.getProperty("user.dir");
        StringBuilder sb = new StringBuilder();
        //创建存储文件的路径
        sb.append(userDir).append(File.separator).append("filesystem");
        File filesystemPath = new File(sb.toString());
        if(!filesystemPath.exists()){
            filesystemPath.mkdir();
        }
        //创建填充数据的文件
        sb.append(File.separator).append(fileName).append(".").append(UUID.randomUUID().toString().substring(0,5));
        excelFile = sb.toString();
log.debug(">>>the excelFile is {}", excelFile);
        File file = new File(excelFile);

FileOutputStream fos  = null;
try {
    if(!file.exists()) {
        file.createNewFile();
    }
    fos = new FileOutputStream(file);
} catch (IOException e) {
    throw new FileNotFoundException("Open stream error "+excelFile);
}

        if(in == null ){  throw new FilerException(excelFile+"下文件为空."); }
        try{
            Context context = new Context();
            context.putVar("emps", emps);
            JxlsHelper.getInstance().processTemplate(in, fos, context);
            fos.close();
            //TODO 此处可以有数据
        }catch (Exception e){
            log.error("文件读取出错:"+ e );
        }finally {
            try {
                os.close();
                in.close();
            } catch (IOException e) {
                log.error(String.valueOf(e));
            }
        }

    }

}
