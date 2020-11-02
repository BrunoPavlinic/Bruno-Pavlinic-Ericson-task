package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.Error;
import com.example.demo.response.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService implements IUserService{

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response login(User user) {
        Response response = new Response();
        if(this.isValid(response, user)) {
            setHash(user);
            User filteredUser = userRepository.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            if(filteredUser == null) {
                response.addError(new Error("Invalid login credentials", "general"));
                response.setValid(false);
            } else {
                response.setResponseObject(filteredUser);
            }
        }
        return response;
    }

    @Override
    public Response createUser(User user) {
        Response response = new Response();
        try {
            if(isValid(response, user)) {
                setHash(user);
                userRepository.save(user);
                User filteredUser = userRepository.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
                response.setResponseObject(filteredUser);
            }
        } catch (DataIntegrityViolationException exception) {
            response.addError(new Error("This username is already taken", "general"));
            response.setValid(false);
        }
        return response;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    private void setHash(User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] byteHash = md.digest(user.getPassword().getBytes());
        StringBuilder hash = new StringBuilder();
        for (byte b : byteHash) {
            hash.append(String.format("%02x", b));
        }
        user.setPassword(hash.toString());
    }

    private boolean isValid(Response response, User user) {
        boolean isValid = true;
        if(user.getUsername().isEmpty()) {
            response.addError(new Error("Username is mandatory", "username"));
            response.setValid(false);
            isValid = false;
        }

        if(user.getPassword().isEmpty()) {
            response.addError(new Error("Password is mandatory", "password"));
            response.setValid(false);
            isValid = false;
        }
        return isValid;
    }

}
