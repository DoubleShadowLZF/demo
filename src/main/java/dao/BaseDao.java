package dao;

import java.util.List;

public interface BaseDao {
    int insert(Object obj);

    int delete(Long id );

    int update(Object obj);

    int query(Long id);

    List list();
}
