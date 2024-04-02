package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Mentor;

import java.util.List;
import java.util.Map;

public class MentorDao extends BaseDao<Mentor> {
    public MentorDao() {
        super("mentor");
    }

    public boolean insert(Mentor mentor) {
        Map<String, Object> map = mentor.toMap();
        return super.insert(map);
    }


    public int delete(Mentor mentor) {
        Map<String, Object> map = mentor.toMap();
        return super.delete(map);
    }

    public List<Mentor> query(Mentor mentor, int start, int end) {
        Class<Mentor> clazz = Mentor.class;
        Map<String, Object> map = mentor.toMap();
        return super.query(clazz, map, start, end);
    }


    public int update(Mentor mentor, Mentor mapCondition) {
        Map<String, Object> map = mentor.toMap();
        Map<String, Object> condition = mapCondition.toMap();
        return super.update(map, condition);
    }
}
