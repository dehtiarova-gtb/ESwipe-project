package project.repo;

import project.models.User;

import java.util.HashSet;
import java.util.Set;

public class UserRepo {
    public static Set<User> getAll(){
        HashSet<User> users = new HashSet<>();
        User user1 = new User(10L,"Валерия Дегтярьова","username1","password1","valeria@gmail.com");
        User user2 = new User(11L,"Григорий Дегтярьов","hgrigorii","54321","hgrigorii@gmail.com");
        users.add(user1);
        users.add(user2);
        return users;
    }
}
