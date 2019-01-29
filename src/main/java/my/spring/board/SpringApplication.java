package my.spring.board;


import my.spring.board.config.ApplicationConfig;
import my.spring.board.dao.BoardDao;
import my.spring.board.dao.UserDao;
import my.spring.board.dto.Board;
import my.spring.board.dto.User;
import my.spring.board.service.BoardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringApplication {
    public static void main(String[] args){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UserDao userDao = applicationContext.getBean(UserDao.class);
        BoardDao boardDao = applicationContext.getBean(BoardDao.class);
        BoardService boardService = applicationContext.getBean(BoardService.class);
//        List<User> users = userDao.selectAll();
//        for(User user : users){
//            System.out.println(user);
//        }
//        User user = userDao.selectUserByEmail("urstory2@gmail.com");
//        System.out.println(user);

//        User user = new User();
//        user.setName("홍길동");
//        user.setEmail("hong@hong.com");
//        user.setNickname("hong");
//        user.setPasswd("1324");
//        long id = userDao.addUser(user);
//        System.out.println(id);

        List<User> userList = userDao.selectAll();
        for(User u : userList){
            System.out.println(u);
        }
        System.out.println("--------------------------");
        List<Board> boardList = boardDao.selectAll();
        for(Board b : boardList){
            System.out.println(b);
        }
        System.out.println("--------------------------");
        Board board = boardDao.selectBoardById(2L);
        System.out.println(board);
  //      userDao.updateUser(8L, "고길동", "둘리아빠");
        System.out.println("==========================");
        Board board2 = boardService.getBoard(1L);
        System.out.println(board2);

    }
}
