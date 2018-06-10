package com.sliit.ds.FastFoodOnlineIT16032798.repository;

import com.sliit.ds.FastFoodOnlineIT16032798.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUid(long uid);
    User findByEmail(String email);
    User findByName(String name);
    // TODO find by mobile number.

    List<User> findUsersByNameContaining(String name);

    void deleteByEmail(String email);
}
