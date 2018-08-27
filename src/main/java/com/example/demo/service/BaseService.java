package com.example.demo.service;

import java.util.List;

public interface BaseService {
    int insert(Object obj);

    int delete(Long id );

    int update(Object obj);

    int query(Long id);

}
