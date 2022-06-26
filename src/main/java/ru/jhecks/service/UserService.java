package ru.jhecks.service;

import ru.jhecks.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateUser(long id, User user);
    void deleteUser(long id);
    User getUser(long id);
    User getUser(String username);
    List<User> getAllUsers();
}
