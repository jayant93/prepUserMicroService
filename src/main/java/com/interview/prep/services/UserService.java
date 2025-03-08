package com.interview.prep.services;

import com.interview.prep.exceptions.UserCreationFailedException;
import com.interview.prep.exceptions.UserNotFoundException;
import com.interview.prep.records.UserRecord;

import java.util.List;

public interface UserService {

    public void createUser(UserRecord userRecord) throws UserCreationFailedException;

    public UserRecord getUserById(Long id) throws UserNotFoundException;

    public List<UserRecord> getAllUsers();

    public void deleteUserById(Long id) throws UserNotFoundException;

    public void updateUserById(Long id,UserRecord userRecord) throws UserNotFoundException;

    public UserRecord getUserByName(String name) throws UserNotFoundException;

    public void deleteUserByName(String name) throws UserNotFoundException;

    public void updateUserByName(UserRecord userRecord) throws UserNotFoundException;


}
