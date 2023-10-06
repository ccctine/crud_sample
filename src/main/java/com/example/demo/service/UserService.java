package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public Object createUser(User reqData) {
        return userRepository.save(reqData);
        
    }

    public Object getAllUsers() {
        return userRepository.findAll();
    }

    public User isDataExist(User reqData) {
        return userRepository.findByEmailAndMobileNum(reqData.getEmail(), reqData.getMobileNum());

    }

    public Object getUserById(Long id){
        return userRepository.findById(id);
    }

    public Object updateUser(User reqData, User isData) {
        isData.setName(reqData.getName());
        isData.setEmail(reqData.getEmail());
        isData.setMobileNum(reqData.getMobileNum());
        isData.setPassword(reqData.getPassword());
        return userRepository.save(isData);
    }

    public Object deleteUserById(Long id){
        userRepository.deleteById(id);
        return null;
    }
}
