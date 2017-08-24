package securityapplication.service;

import securityapplication.model.User;

/**
 * Created by Artem Solomatin on 23.08.17.
 * Authorization
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
