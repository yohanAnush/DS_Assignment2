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

    private static List<User> userList;


    @Override
    public User findByUid(long uid) {
        return userRepository.findByUid(uid);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findUsersHavingName(String name) {
        Iterable<User> userIterable = userRepository.findUsersByNameContaining(name);
        List<User>userList = new ArrayList<>();

        userIterable.forEach(userList::add);
        return userList;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User userUpdate) {
        System.out.println(userUpdate.getUid());
        User userExisting = userRepository.findByUid(userUpdate.getUid());

        // update everything but uid.
        if (userExisting != null) {
            userExisting.setEmail(userUpdate.getEmail());
            userExisting.setName(userUpdate.getName());
            userExisting.setMobileNumbers(userUpdate.getMobileNumbers());

            saveUser(userExisting);
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {

        Iterable<User> userIterable = userRepository.findAll();
        userList = new ArrayList<>();   // we are using the static list in the class.

        userIterable.forEach(userList::add);
        return userList;
    }

    @Override
    public boolean isUserExist(User user) {
        return userRepository.existsById(Long.toString(user.getUid()));
    }
}
