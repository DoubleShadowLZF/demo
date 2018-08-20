package com.example.demo.redis.dao;

import com.example.demo.redis.component.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDao {
    private Map<Integer , User> users = null;


    {
        users = new HashMap<>();
        users.put(0 , new User("Double",23));
        users.put(1 , new User("Jack",24));
        users.put(2 , new User("Bee",25));
        users.put(3 , new User("Pee",26));
    }

    @Cacheable(value="user",key="'user_'+#username")
    public int insertUser(User user){
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if(u.getName().equals(user.getName()) && u.getAge().equals(user.getAge())){
                return 0 ;
            }
        }
        return 1;
    }

    @CacheEvict(value="user",key="'user_'+#username")
    public int updateUser(Integer userId , User user){
        users.put(userId , user);
        return 1;
    }

    @Cacheable(value="user",key="'user_'+#username")
    public Map<Integer , User> queryList() {
        return users;
    }

    @Cacheable(value="user",key="'user_'+#username")
    public User queryUser(Integer userId , User user) {
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
}
