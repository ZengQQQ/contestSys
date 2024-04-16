package com.game.domain;

import com.game.domain.secondaryDao.Team;
import lombok.Data;

import java.util.List;

@Data
public class TeamGather {
    private Team team;
    private List<Mentor> mentorList;
    private List<Student> studentList;

    public TeamGather() {
    }

    public TeamGather(Team team, List<Mentor> mentorList, List<Student> studentList) {
        this.team = team;
        this.mentorList = mentorList;
        this.studentList = studentList;
    }
}
