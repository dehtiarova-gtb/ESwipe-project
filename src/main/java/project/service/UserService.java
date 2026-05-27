package project.service;

import project.models.User;
import project.repo.UserRepo;

import java.util.Set;

public class UserService {
    public enum LoginResult {
        SUCCESS,
        USER_NOT_FOUND,
        WRONG_PASSWORD
    }
    public LoginResult login(String username, String password) {
        Set<User> users = UserRepo.getAll();

        User user = users.stream().filter(u ->
                u.getUsername().equals(username))
                .findFirst().orElse(null);

        if (user == null)
            return LoginResult.USER_NOT_FOUND;
        if (!user.getPassword().equals(password))
            return LoginResult.WRONG_PASSWORD;
        return LoginResult.SUCCESS;
    }




}
