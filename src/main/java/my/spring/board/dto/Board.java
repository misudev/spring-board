package my.spring.board.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
public class Board {

    private Long id;
    private Long userId;
    private String nickname;
    private String title;
    private String content;
    private Date regdate;
    private int readCount;

    private Long thread;
    private int depth;

    public Board(){
        this.depth = 0;
        this.readCount = 0;
        this.regdate = new Date();
    }




}
