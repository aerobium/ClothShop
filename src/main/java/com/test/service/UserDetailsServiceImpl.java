package com.test.service;

/**
 * Created by RAYANT on 26.04.2015.
 */

import com.test.entity.User;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository = new UserRepository();
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // с помощью нашего сервиса UserService получаем User


        User user = null;
        user = userRepository.getUser(login);
        // указываем роли для этого пользователя
        Set<GrantedAuthority> roles = new HashSet();

        if (!user.getIsAdmin().equals("1"))
        {roles.add(new SimpleGrantedAuthority("ROLE_USER"));}

        else {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        // на основании полученныйх даных формируем объект UserDetails
        // который позволит проверить введеный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }

}