package org.example.demo.redis.service.impl;

import org.example.demo.redis.component.User;
import org.example.demo.redis.dao.UserDao;
import org.example.demo.redis.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * redis 获取数据服务实现类
 *
 * Spring Cache是作用在方法上的,其核心思想是:
 * 在调用一个缓存方法时会把该方法参数和返回结果作为一个键值对存放在缓存中,等到下次利用同样的参数来调用该方法时不再执行该方法,
 * 而是直接从缓存中获取结果进行返回.所以在使用Spring Cache的时候,应该保证缓存的方法对于相同的方法参数要有相同的返回结果.
 *
 * 使用Spring Cache的方法:
 *  1.声明某些方法使用缓存
 *  2.配置Spring对Cache 的支持
 *
 * SpringCache的关键注解:
 *  1.@Cacheable 标记的方法在执行后SpringCache 将缓存其返回结果;
 *  2.@CacheEvict 标记的方法会在方法执行前或后移除SpringCache 中的某些元素;
 *
 * @author Double
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseUserService {

    @Autowired
    private UserDao userDao;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    /**
     * @Description 查询场景应用
     *  在 redis缓存中查询是否存在需要查找的数据,
     *  如果没有,则查询数据库,将数据缓存到redis中,并返回数据;
     *  如果存在,则直接从redis 缓存中取出数据,并返回数据.
     * @param [userId, user]
     * @return com.example.demo.redis.component.User
     * @Data 2018/8/21 9:17
     * @author Double
     */
    @Override
    public User queryUser(User user) {
        return userDao.queryUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    /**
     * @Description 更新场景应用
     *  在 redis缓存中查询是否存在需要更新或删除的数据,
     *  如果没有,则直接更新或删除数据库中的数据;
     *  如果存在,则先更新或删除数据库中的数据,再操作redis中的数据.
     * @param [userId, user]
     * @return int
     * @Data 2018/8/21 9:20
     * @author Double
     */
    @Override
    public int updateUser(Integer userId , User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Map<Integer , User> list() {
        return userDao.queryList();
    }
}
