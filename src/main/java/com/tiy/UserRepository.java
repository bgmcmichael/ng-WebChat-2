package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by fenji on 9/26/2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByNameAndPassword(String name, String password);
}
