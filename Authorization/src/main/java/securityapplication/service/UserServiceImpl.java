package securityapplication.service;

import securityapplication.dao.RoleDao;
import securityapplication.dao.UserDao;
import securityapplication.model.Role;
import securityapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artem Solomatin on 23.08.17.
 * Authorization
 *
 * Provide service for registering account
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Выставляет юзеру роли, шифрует пароль и вызывает метод из спринга для сохранения в бд
	 */
	@Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
