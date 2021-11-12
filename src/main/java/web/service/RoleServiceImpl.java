package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;


import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{



    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
//    @Transactional(readOnly = true)
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
//    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
//    @Transactional
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
//    @Transactional(readOnly = true)
    public void deleteRole(long id) {
        roleDao.deleteRole(id);
    }

    @Override
//    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
