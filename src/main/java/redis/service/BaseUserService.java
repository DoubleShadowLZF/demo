package redis.service;

import redis.component.User;

import java.util.Map;

public abstract class BaseUserService {
    public abstract User queryUser(User user);

    public abstract int insertUser(User user);

    public abstract int updateUser(Integer userId, User user);

    public abstract Map<Integer, User> list();
}
