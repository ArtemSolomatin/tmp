package securityapplication.service;

/**
 * Created by Artem Solomatin on 23.08.17.
 * Authorization
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
