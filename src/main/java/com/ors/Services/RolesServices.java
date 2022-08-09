package com.ors.Services;

import com.ors.Models.Roles;
import com.ors.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServices {
    @Autowired private RolesRepository rolesRepository;
    public Roles getCustomerRole(String customer) {
        Roles role=rolesRepository.findByRoleName(customer);
        return role;
    }
}
