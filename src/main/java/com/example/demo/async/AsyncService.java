package com.example.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Double
 */
@Service
@Slf4j
public class AsyncService {

    /**
     * @Description 与方法 annocation 作对比,前端请求后,立即返回请求内容.
     * @param []
     * @return org.springframework.http.ResponseEntity
     * @Data 2018/8/28 22:15
     * @author Double
     */
    public ResponseEntity test(){
        return ResponseEntity.ok("test");
    }

    @Async
    public String test01(){
        try {
            log.info("1."+Thread.currentThread().getName()+ "test01:begin....");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        log.info("1."+Thread.currentThread().getName()+ "test01:end....");
        return Thread.currentThread().getName()+ "test01";
    }

    public String test02(){
        log.info("2."+Thread.currentThread().getName()+ "test03:begin....");
        return Thread.currentThread().getName()+"test02";
    }
    public String test03(){
        /*try {
            log.info("3."+Thread.currentThread().getName()+ "test03:begin....");
            Thread.sleep(3000);
            log.info("3."+Thread.currentThread().getName()+ "test03:end....");
        } catch (InterruptedException e) {
        }*/
        return Thread.currentThread().getName()+"test03";
    }


    /**
     * @Description 异步执行操作,但是无响应信息
     * @param []
     * @return org.springframework.http.ResponseEntity
     * @Data 2018/8/28 22:16
     * @author Double
     */
    @Async
    public ResponseEntity annocation(){
        log.debug("{}异步開始>>:",Thread.currentThread().getName()) ;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("{}异步执行完毕...",Thread.currentThread().getName());
        return ResponseEntity.ok(Thread.currentThread().getName()+"执行完毕");
    }

    /**
     * @Description 异步执行操作,并且会返回 Future对象内的信息
     * @param []
     * @return java.util.concurrent.Future<java.util.Map>
     * @Data 2018/8/28 22:16
     * @author Double
     */
    @Async
    public Future<Map> annocationFuture(){
        log.debug("{}异步開始>>:",Thread.currentThread().getName()) ;
        Future<Map> futrue = null ;
        try {
            Thread.sleep(3000);
            Map map = new HashMap<>();
            map.put("thread",Thread.currentThread().getName()+"执行完毕");
            futrue = new AsyncResult<Map>(map);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("{}异步执行完毕...",Thread.currentThread().getName());
        return futrue;
    }

    public Future<Map> customReturnFutrue(){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<Map> result = executor.submit(new RunnableTask());

        return result;
    }

    class RunnableTask implements Callable<Map>{
        @Override
        public Map call() throws Exception {
            Thread.sleep(100000);
            Map map = new HashMap();
            map.put("thread",Thread.currentThread().getName());
            return map;
        }
    }


}
