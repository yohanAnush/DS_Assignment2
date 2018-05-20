package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.User;
import com.sliit.ds.FastFoodOnlineIT16032798.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUid(long uid) {
        return userRepository.findByUid(uid);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public long getUidOfEmail(String email) {
        User user = userRepository.findByEmail(email);
        return (user != null) ? user.getUid() : 0;
    }

    @Override
    public String getEmailOfUid(long uid) {
        User user = userRepository.findByUid(uid);
        return (user != null) ? user.getEmail() : "";
    }

    @Override
    public List<User> findUsersHavingName(String name) {
        return userRepository.findUsersByNameContaining(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(long uid, User userUpdate) {
        if (uid != userUpdate.getUid()) { userUpdate.setUid(uid); } // in case the frontend sends a dummy id.

        User userExisting = userRepository.findByUid(userUpdate.getUid());

        if (userExisting != null) {
            userExisting.setEmail(userUpdate.getEmail());
            userExisting.setName(userUpdate.getName());
            userExisting.setMobileNumber(userUpdate.getMobileNumber());
            userExisting.setAddress(userUpdate.getAddress());
            saveUser(userExisting);
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(long uid) {
        return userRepository.existsById(Long.toString(uid));
    }

    @Override
    public boolean isPasswordCorrect(String email, String password) {
        User user = userRepository.findByEmail(email);
        boolean validity = (user != null) && (user.getPassword().equals(password));

        return validity;
    }

}
