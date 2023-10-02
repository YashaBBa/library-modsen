package com.modsen.library.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    private final List<User> users = Arrays.asList(
            new User("yashaborovcov@gmail.com"
                    , "password"
                    , Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))

    );

    public UserDetails findUserByEmail(String email) {
        return users.stream().
                filter(x -> x.getUsername().equals(email)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user"))
                ;
    }

}
