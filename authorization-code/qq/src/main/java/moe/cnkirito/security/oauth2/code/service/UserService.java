package moe.cnkirito.security.oauth2.code.service;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moe.cnkirito.security.oauth2.code.model.UserDemo;



@Repository
public interface UserService extends JpaRepository<UserDemo, Serializable>{
	
	@Query(value="select t from UserDemo t where t.username=?1")
	UserDemo findByName(@Param("name") String name);
	
	@Query(value="select t from UserDemo t where t.id=?1")
	UserDemo findById(@Param("id") String id);
}
