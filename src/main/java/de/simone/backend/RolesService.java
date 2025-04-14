package de.simone.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RolesService extends TAService<Role> {

    public RolesService() {
        super(Role.class);
    }

    @Override
    public Role get(Long id) throws WebApplicationException {
        return getImpl(id);

    }

    @Transactional
    public Response delete(Long id) {
        Role role = Role.findById(id);
        if (role.isSpetialRole())
            return getBadRequestResponse("Response.role.spetial", "userName", role.roleName);

        return deleteImpl(id);
    }

    @Transactional
    public Response save(Role entity) {
        // new? check if the role allready exits
        if (entity.isNewEntity() && !isRoleAvailable(entity.roleName))
            return getBadRequestResponse("Response.role.exist", "role",
                    entity.roleName);

        // all es klar
        return saveImpl(entity);
    }

    /**
     * return true if the rolename is available
     * 
     * @param shortName - the name to check
     * 
     * @return true if account is unique
     */
    public boolean isRoleAvailable(String role) {
        long count = Role.count("roleName = ?1", role);
        return count == 0;
    }

    @Override
    @Transactional
    public Response duplicate(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'duplicate'");
    }

}
