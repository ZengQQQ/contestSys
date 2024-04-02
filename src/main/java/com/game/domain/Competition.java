package com.game.domain;

import com.game.utils.Level;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 竞赛信息实体类
 */
@Data
public class Competition {

    private Integer c_id=null;
    private String c_name=null;
    private Level c_level=null;
    private LocalDateTime c_sign_t=null;
    private String c_url=null;
    private String c_img=null;

    public Competition() {
    }

}
