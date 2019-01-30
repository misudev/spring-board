package my.spring.board.service;


import my.spring.board.dao.BoardDao;
import my.spring.board.dto.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements  BoardService {
    private BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao){
        this.boardDao = boardDao;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Board> getBoards(int start, int limit) {
        return boardDao.selectBoardByPaging(start, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardDao.selectBoardById(id);
    }

    @Override
    @Transactional
    public void updateReadCount(Long id){
        boardDao.updateReadCount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCountBoard() {
        return boardDao.getCountBoard();
    }

    @Override
    @Transactional
    public void deleteBoard(Long id, Long sigendId) {
        Board board = boardDao.selectBoardById(id);
        // 유저가 작성한 글이 아니면 삭제X
        if(sigendId != board.getUserId()) return;
        // 답글이 있으면 삭제X, 없으면 삭제 O
        if(!boardDao.existReply(board.getThread())){
            // board 삭제하고, count-board 마이너스
            boardDao.deleteBoard(id);
            boardDao.updateCountBoardMinus();
            if(boardDao.getMaxThread()==board.getThread()){
                // 가장 최신 글일 경우 Max Thread값 감소..
                boardDao.updateMaxThreadMinus();
            }
        }
    }
//원글 달기...
    @Override
    @Transactional
    public void addBoard(Board board) {
        board.setThread(boardDao.getMaxThread()*100 + 100); // Thread값 구하기.
        boardDao.updateMaxThread(); //  MaxThread + 1
        boardDao.updateCountBoard(); // CountBoard + 1
        boardDao.addBoard(board);
    }
//답글 달기...
    @Override
    @Transactional
    public void addReply(long parentId, Board board) {
        Board parentBoard = boardDao.selectBoardById(parentId);
        board.setThread(parentBoard.getThread() - 1);
        board.setDepth(parentBoard.getDepth() + 1);
        boardDao.addBoard(board);
        boardDao.updateCountBoard();
    }

    @Override
    @Transactional
    public void updateBoard(Board board) {
        boardDao.updateBoard(board.getTitle(), board.getContent(), board.getId());
    }

}
