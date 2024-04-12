package com.game.domain;

import com.game.domain.secondary.Team;
import lombok.Data;

import java.util.List;

@Data
public class TeamRelation {
    private Team team;
    private List<Mentor> mentorList;
    private List<Student> studentList;

    public TeamRelation() {
    }

    public TeamRelation(Team team, List<Mentor> mentorList, List<Student> studentList) {
        this.team = team;
        this.mentorList = mentorList;
        this.studentList = studentList;
    }
}
