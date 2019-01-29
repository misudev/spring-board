package my.spring.board.service;

import my.spring.board.dto.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public User getUserByEmail(String email);
    public List<User> getUsers();
}
