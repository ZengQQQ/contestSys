package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Mentor;

import java.util.List;
import java.util.Map;

public class MentorDao extends BaseDao<Mentor> {
    public MentorDao() {
        super("mentor");
    }

    @Override
    public boolean insert(Map<String, Object> map) {
        return super.insert(map);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return super.delete(map);
    }

    public List<Mentor> query(Map<String, Object> map, int start, int end) {
        Class<Mentor> clazz = Mentor.class;
        return super.query(clazz, map, start, end);
    }

    @Override
    public int update(Map<String, Object> map, Map<String, Object> condition) {
        return super.update(map, condition);
    }
}
