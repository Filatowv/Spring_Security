package web.dao;

import web.model.Role;
import web.model.User;
import java.util.List;



public interface RoleDao {

    List<Role> getAllRole();
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(long id);
    Role getRoleByName(String name);
}
