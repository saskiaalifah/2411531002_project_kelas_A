package DAO;

import java.util.List;

import model.User;

public interface UserDao {
    void save(User user);
    List<User> show();
    void delete(String id);
    void update(User user);
}