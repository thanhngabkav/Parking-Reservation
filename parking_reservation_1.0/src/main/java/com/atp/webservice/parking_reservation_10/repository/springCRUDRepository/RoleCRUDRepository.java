package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleCRUDRepository extends CrudRepository<Role, Integer> {

    Role findRoleByRoleName(String roleName);
}
