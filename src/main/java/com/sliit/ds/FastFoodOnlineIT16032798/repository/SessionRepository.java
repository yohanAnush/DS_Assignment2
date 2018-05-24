package com.sliit.ds.FastFoodOnlineIT16032798.repository;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessionRepository extends MongoRepository<Session, String> {

    Session findByUid(long uid);
    Session findByAuthKeyOfUid(long authKey);
    List<Session> findByRole(String role);

    void deleteByAuthKeyOfUid(long authKey);
    void deleteByUid(long uid);
}
