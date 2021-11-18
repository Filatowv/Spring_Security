package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRole() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void deleteRole(long id) {
        entityManager.remove(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select role from Role role where role.role =:roleName ", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
    }
}
