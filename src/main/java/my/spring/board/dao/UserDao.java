package my.spring.board.dao;

import my.spring.board.dto.User;

import java.util.List;

public interface UserDao {
    public List<User> selectAll();
    public User selectUserByEmail(String email);
    public Long addUser(User user); // 생성된 id(Long type)를 리턴한다.
    public void updateUser(Long id, String name, String nickname);
}
