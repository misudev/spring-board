package my.spring.board.dao;

import my.spring.board.dto.Board;

import java.util.List;

public interface BoardDao {
    public List<Board> selectAll();
    public List<Board> selectBoardByPaging(int start, int limit);
    public Board selectBoardById(Long id);
    public Long addBoard(Board board); // 생성된 id(Long type)를 리턴한다.
    public void updateBoard(String title, String content, Long id);
    public void updateReadCount(Long id);
    public void deleteBoard(Long id);

    public  void updateThreadMinus(long thread);
    public void updateThreadPlus(long min, long max);
    public boolean existReply(long thread);

    //manage 테이블
    public long getMaxThread();
    public long getCountBoard();
    public void updateMaxThread();
    public void updateMaxThreadMinus();
    public void updateCountBoard();
    public void updateCountBoardMinus();

    // search

//    public List<Board> getBoardsByTitle(String keyword, long start, int limit);
//    public List<Board> getBoardsByContent(String keyword, long start, int limit);
//    public long countByTitle(String keyword);
//    public long countByContent(String keyword);


}
