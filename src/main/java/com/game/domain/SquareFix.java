package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.List;

@Data
public class SquareFix {
    private Square square;
    private List<StudentTeamLink> studentTeamLinks;
    private List<User> member;

    public SquareFix() {
    }

    public SquareFix(Square square, List<StudentTeamLink> studentTeamLinks, List<User> member) {
        this.square = square;
        this.studentTeamLinks = studentTeamLinks;
        this.member = member;
    }
}
