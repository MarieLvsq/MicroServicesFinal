package com.urbanisationsi.microservicessecurite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.urbanisationsi.microservicessecurite.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}
