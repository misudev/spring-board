package my.spring.board;

import lombok.ToString;

@ToString
public class Paging {
    private final static int BLOCK_SIZE = 10;
    private final static int SIZE = 5;
    private int boardCount = 0;
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    //게시판 시작 넘버.
    private int start = 0;


    public Paging(long boardCount){
        this.boardCount = (int)boardCount;
        lastPageNum = (int)boardCount/SIZE + ((boardCount%SIZE) == 0 ? 0 : 1);
    }

    public int getBlockStartNum() {
        return blockStartNum;
    }

    public void setBlockStartNum(int blockStartNum) {
        this.blockStartNum = blockStartNum;
    }

    public int getBlockLastNum() {
        return blockLastNum;
    }

    public void setBlockLastNum(int blockLastNum) {
        this.blockLastNum = blockLastNum;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public int getBoardCount() {
        return boardCount;
    }

    public void setBoardCount(int boardCount) {
        this.boardCount = boardCount;
    }

    public void makeBlock(int now){
        start = SIZE * now - SIZE;
        blockStartNum = ((now + 1)/BLOCK_SIZE) * BLOCK_SIZE + 1;
        blockLastNum = (now + BLOCK_SIZE - 1) < lastPageNum ? now + BLOCK_SIZE - 1 : lastPageNum;

    }

    public static int getSIZE() {
        return SIZE;
    }

    public int getStart() {
        return start;
    }


    /*
    public void makeLastPageNum(long boardCount){
        lastPageNum = (int)boardCount/SIZE + ((boardCount%SIZE) == 0 ? 0 : 1);

    }
    */
}
