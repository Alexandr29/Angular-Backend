package com.nixsolutions.dao;

import com.nixsolutions.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
