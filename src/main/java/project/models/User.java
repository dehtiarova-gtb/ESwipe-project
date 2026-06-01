package project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

}
