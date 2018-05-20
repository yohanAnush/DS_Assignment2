package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.User;

import java.util.List;

public interface UserService {

    User findByUid(long uid);

    User findByEmail(String email);

    long getUidOfEmail(String email);

    String getEmailOfUid(long uid);

    List<User> findUsersHavingName(String name);

    void saveUser(User user);

    void updateUser(long uid, User userUpdate);

    void deleteUserByEmail(String email);

    List<User> findAllUsers();

     boolean isUserExist(long uid);

     boolean isPasswordCorrect(String email, String password);

}
