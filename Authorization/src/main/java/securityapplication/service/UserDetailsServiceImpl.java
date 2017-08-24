package securityapplication.service;

import org.springframework.stereotype.Service;
import securityapplication.dao.UserDao;
import securityapplication.model.Role;
import securityapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artem Solomatin on 20.08.17.
 * Authorization
 *
 * Для того, чтобы связать сервис UserService со Spring Security, нужно реализовать специальный интерфейс фреймворка Spring Security который позволит выполнять аутентификацию пользователя на основании данных полученых с UserService
 *
 * To implement login/authentication with Spring Security, we need to implement org.springframework.security.core.userdetails.UserDetailsService interface
 * Authorization is implemented via XML file configuration appconfig-security.xml
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
