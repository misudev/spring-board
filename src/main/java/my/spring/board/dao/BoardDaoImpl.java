package my.spring.board.dao;

import my.spring.board.dto.Board;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.spring.board.dao.BoardDaoSqls.*;

//static 으로 import해야 인스턴스 생성없이 사용 가능.

// 컴포넌트스캔 - 해당객체가 Bean으로 등록된다.
@Repository
public class BoardDaoImpl implements BoardDao {
    //insert 할 때 필요하다.
    private SimpleJdbcInsert simpleJdbcInsert;
    // ?에 들어갈 변수를 이름으로 넣을 수 있다.
    private NamedParameterJdbcTemplate jdbc;
    // 조회한 결과를 담을 RowMapper
    private RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);

    // 스프링 컨테이너는 인스턴스를 생성하려고 생성자를 호출한다.
    // 생성자를 호출하는데, DataSource를 주입한다. (생성자 주입)
    public BoardDaoImpl(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public List<Board> selectAll() {
        return jdbc.query(SELECT_BOARDS, rowMapper);
    }

    @Override
    public List<Board> selectBoardByPaging(int start, int limit) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("limit", limit);

        return jdbc.query(SELECT_BY_PAGING, paramMap, rowMapper);
    }

    @Override
    public Board selectBoardById(Long id) {
        Board board = null;
        Map<String, Object> paramMap = Collections.singletonMap("id",id);
        try{
            board = jdbc.queryForObject(SELECT_BOARD_BY_ID, paramMap, rowMapper);
        }catch (Exception ex){
            board = null;
        }
        return board;
    }

    @Override
    // 답글이든 원글이든 둘 다 받도록 한다.
    public Long addBoard(Board board) {
//thread, user_id, nickname, title, content
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("thread",board.getThread());
        paramMap.put("depth",board.getDepth());
        paramMap.put("user_id", board.getUserId());
        paramMap.put("nickname", board.getNickname());
        paramMap.put("title", board.getTitle());
        paramMap.put("content", board.getTitle());
        paramMap.put("regdate", board.getRegdate());
        paramMap.put("read_count", board.getReadCount());
        Number number = simpleJdbcInsert.executeAndReturnKey(paramMap);
        return number.longValue();
    }

    @Override
    public void updateBoard(String title, String content, Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", title);
        paramMap.put("content", content);
        paramMap.put("id", id);
        jdbc.update(UPDATE, paramMap);
    }

    @Override
    public void updateReadCount(Long id) {
        Map<String, Object>paramMap = Collections.singletonMap("id", id);
        jdbc.update(UPDATE_READCOUNT, paramMap);
    }

    @Override
    public void deleteBoard(Long id) {
        Map<String, Object>paramMap = Collections.singletonMap("id", id);
        jdbc.update(DELETE, paramMap);
    }

    @Override
    public void updateThreadMinus(long thread) {
        Map<String, Object>paramMap = new HashMap<>();
        paramMap.put("min", (thread/100)*100);
        paramMap.put("max", thread + 1);
        jdbc.update(UPDATE_THREAD_MINUS, paramMap);
    }

    @Override
    public void updateThreadPlus(long min, long max) {
        Map<String, Object>paramMap = new HashMap<>();
        paramMap.put("min", min);
        paramMap.put("max", max);
        jdbc.update(UPDATE_THREAD_PLUS, paramMap);
    }

    @Override
    public boolean existReply(long thread) {
        Map emptyMap = Collections.emptyMap();
        int result = jdbc.queryForObject(EXIST_BY_THREAD, emptyMap, Integer.class);
        return result == 1 ? true : false;
    }

// manage table

    @Override
    public long getMaxThread() {
        Map emptyMap = Collections.emptyMap();
        long result = jdbc.queryForObject(SELECT_MAX_THREAD, emptyMap, Long.class);
        return result;
    }

    @Override
    public long getCountBoard() {
        Map emptyMap = Collections.emptyMap();
        long result = jdbc.queryForObject(SELECT_COUNT_BOARD, emptyMap, Long.class);
        return result;
    }

    @Override
    public void updateMaxThread() {
        Map emptyMap = Collections.emptyMap();
        jdbc.update(UPDATE_MAX_THREAD, emptyMap);
    }

    @Override
    public void updateMaxThreadMinus() {
        Map emptyMap = Collections.emptyMap();
        jdbc.update(UPDATE_MAX_THREAD_MINUS, emptyMap);
    }

    @Override
    public void updateCountBoard() {
        Map emptyMap = Collections.emptyMap();
        jdbc.update(UPDATE_COUNT_BOARD, emptyMap);
    }

    @Override
    public void updateCountBoardMinus() {
        Map emptyMap = Collections.emptyMap();
        jdbc.update(UPDATE_COUNT_BOARD_MINUS, emptyMap);
    }
}
