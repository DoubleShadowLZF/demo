package org.example.demo.redis.dao;

import org.example.demo.redis.component.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description Dao层 缓存操作
 * @Data 2018/8/21 9:40
 * @author Double
 */
@Component
@Slf4j
public class UserDao {
    @Autowired
    private ValueOperations valueOperations;

    private final String USER_PREFIX = "user";

    private Map<Integer , User> users = null;
    private Integer initNum = 6 ;

    {
        users = new HashMap<>();
        users.put(0 , new User(0,"Double",23));
        users.put(1 , new User(1,"Jack",24));
        users.put(2 , new User(2,"Bee",25));
        users.put(3 , new User(3,"Pee",26));
    }

    @CacheEvict(cacheNames="user")
    @Async
    public int insertUser(User user){
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if(u.getName().equals(user.getName()) && u.getAge().equals(user.getAge())){
                return 0 ;
            }
        }
        users.put(initNum++,user);
log.debug(">>>insertUser:"+user);
        return 1;
    }

    @CacheEvict(cacheNames="user")
    @Async
    public int updateUser(User user){
        users.put(user.getId() , user);
log.debug(">>>updateUser...");
//        valueOperations.(getPrefixName(user.getId()),user);
        return 1;
    }

    @Cacheable(key="#user.id")
    public Map<Integer , User> queryList() {
log.debug(">>>queryList...");
        return users;
    }

    @Cacheable(cacheNames="user", key="#user.id")
    public User queryUser(User user) {
log.debug(">>>queryUser...");
        Integer userId = user.getId();
        if(userId != null){
            return users.get(userId);
        }
        if(user.getName()!=null){
            for (int i = 0; i < users.size(); i++) {
                if(user.getName() == users.get(i).getName()){
                    return users.get(i);
                }
            }
        }
        return null;
    }

    private String getPrefixName(Integer id){
        return USER_PREFIX + id ;
    }
}