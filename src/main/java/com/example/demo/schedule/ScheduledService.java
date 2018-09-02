package com.example.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Double
 */
@Service
@Slf4j
public class ScheduledService {
    /**
     * @Description second , hour, day of month, month day of week
     *                  0 * * * * MON-FRI  -- 周一到周五每0秒运行一次
     * @param []
     * @return void
     * @Data 2018/9/2 16:52
     * @author Double
     */
    @Scheduled(cron = "0 * * * * SUN-SAT")
    public void hello(){
      log.info("{}","Hello World");
    }
}
