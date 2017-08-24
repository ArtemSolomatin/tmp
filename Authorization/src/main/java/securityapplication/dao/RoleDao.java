package securityapplication.dao;

import securityapplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Artem Solomatin on 23.08.17.
 * Authorization
 */

public interface RoleDao extends JpaRepository<Role, Long> {
}
