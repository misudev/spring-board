package my.spring.board.dao;

public class BoardDaoSqls {
    public static final String SELECT_BOARDS=
            "SELECT id, user_id, title, nickname, regdate, read_count , depth, thread , content FROM board ORDER BY thread DESC";
    public static final String SELECT_BY_PAGING=
            "SELECT id, user_id, title, nickname, regdate, read_count , depth, thread FROM board " +
                    "ORDER BY thread DESC LIMIT :start, :limit";
    public static final String SELECT_BOARD_BY_ID  =
            "SELECT id, user_id, title, content, nickname, regdate, read_count, depth, thread FROM board where id = :id";
    public static final String INSERT_NEW =
            "INSERT INTO board (thread, user_id, nickname, title, content) VALUES (:thread, :userId, :nickname, :title, :content)";
    // "INSERT INTO board (thread, user_id, nickname, title, content) VALUES ((SELECT IFNULL(MAX(thread) + 100, 100) FROM board b),? ,?,?,?)";
    public static final String UPDATE_READCOUNT =
            "Update board SET read_count = read_count + 1 WHERE id = :id";
    public static final String UPDATE =
            "Update board SET title = :title , content = :content WHERE id = :id";
    public static final String DELETE =
            "DELETE FROM board WHERE id = :id";

    public static final String EXIST_BY_THREAD =
            "SELECT EXISTS(SELECT * FROM board WHERE thread = :thread limit 1)";
    public static final String INSERT_REPLY =
            "INSERT INTO board (thread, depth, user_id, nickname, title, content) VALUES (:thread, :depth, :userId, :nickname, :title, :content)";
    public static final String UPDATE_THREAD_MINUS =
            "UPDATE board SET thread = thread - 1 WHERE thread > :min AND thread < :max;";
    public static final String UPDATE_THREAD_PLUS =
            "UPDATE board SET thread = thread + 1 WHERE thread > :min AND thread < :max;";

    // manage  테이블
    public static final String SELECT_MAX_THREAD =
            "SELECT max_thread FROM manage WHERE id = 1";
    public static final String SELECT_COUNT_BOARD =
            "SELECT count_board FROM manage WHERE id = 1";
    public static final String UPDATE_MAX_THREAD =
            "UPDATE manage SET max_thread = max_thread + 1 WHERE id = 1";
    public static final String UPDATE_COUNT_BOARD =
            "UPDATE manage SET  count_board= count_board + 1 WHERE id = 1";
    public static final String UPDATE_MAX_THREAD_MINUS =
            "UPDATE manage SET max_thread = max_thread - 1 WHERE id = 1";
    public static final String UPDATE_COUNT_BOARD_MINUS =
            "UPDATE manage SET  count_board= count_board - 1 WHERE id = 1";

    // search
    public static final String SELECT_SEARCH_BY_CONTENT =
            "SELECT id, user_id, title, nickname, regdate, read_count , depth, thread FROM board " +
                    "WHERE content LIKE ? ORDER BY thread DESC LIMIT ?, ?";
    public static final String SELECT_SEARCH_BY_TITLE =
            "SELECT id, user_id, title, nickname, regdate, read_count , depth, thread FROM board " +
                    "WHERE title LIKE ? ORDER BY thread DESC LIMIT ?, ?";
    public static final String COUNT_SEARCY_BY_CONTENT =
            "SELECT COUNT(*) FROM board WHERE content LIKE ?";
    public static final String COUNT_SEARCY_BY_TITLE =
            "SELECT COUNT(*) FROM board WHERE title LIKE ?";


}
