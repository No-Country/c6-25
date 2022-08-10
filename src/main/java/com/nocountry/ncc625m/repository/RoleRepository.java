package com.nocountry.ncc625m.repository;

import com.nocountry.ncc625m.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName (String name);

}
