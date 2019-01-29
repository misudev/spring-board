package my.spring.board.service;

import my.spring.board.dao.UserDao;
import my.spring.board.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 비지니스 메소드를 가진 Service
@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    //@Autowired
    //private UserDao userDao;  --> 스프링이 아닌 다른 곳에서는 못쓰므로 아래 방법이 더 좋다.

    // UserServiceImpl객체를 생성하려고 UserDaoImpl 객체를 주입한다. (생성자 주입)
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    // 서비스 메소드는 트랜젝션 단위로 동작한다.
    // 트랜젝션 단위로 동작하려면 @Transactional 어노테이션이 붙는다.
    // 조회만 있을 때 ( select .. ) @Transactional(readOnly = true) 를 설정한다.
    // RuntimeException이 발생하면 rollback (Checked Exception은 rollback과 상관이 없다..)
    // 성공하면 commit 한다.
    @Override
    public User addUser(User user) {
        long id = userDao.addUser(user);

        user.setId(id);
        return null;
    }

    // 서비스 메소드 안에서 조회만 있을 경우엔 @Transactional(readOnly=true)
    // 를 설정한다.
    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
     }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.selectAll();

    }
}
