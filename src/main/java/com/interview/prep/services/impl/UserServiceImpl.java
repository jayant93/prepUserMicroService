package com.interview.prep.services.impl;

import com.interview.prep.entities.Address;
import com.interview.prep.entities.User;
import com.interview.prep.exceptions.UpdateOperationFailedException;
import com.interview.prep.exceptions.UserCreationFailedException;
import com.interview.prep.exceptions.UserNotFoundException;
import com.interview.prep.records.AddressRecord;
import com.interview.prep.records.UserRecord;
import com.interview.prep.repositories.UserRepository;
import com.interview.prep.services.UserService;
import com.interview.prep.utility.AddressType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void createUser(UserRecord userRecord) throws UserCreationFailedException {

        User user = new User();
        user.setName(userRecord.name());
        user.setPhoneNumber(userRecord.phoneNumber());
        user.setPassword(userRecord.password());

        try {
            user.setAddress(userRecord.
                    address().
                    stream().
                     map(a -> mapAddresRecordToAddress(a,user))
                    .collect(Collectors.toList()));

            userRepository.save(user);

         }catch (Exception e){
            throw new UserCreationFailedException("User Creation Process Failed : "+e.getMessage());
        }

    }

    /**
     * For creating Address from given AddressRecord
     * @param addressRecord
     * @return
     */
    private Address mapAddresRecordToAddress(AddressRecord addressRecord,User user) {
        Address address = new Address();
        address.setAddressType(addressRecord.addressType());
        address.setArea(addressRecord.Area());
        address.setCity(addressRecord.City());
        address.setHouseNumber(addressRecord.houseNumber());
        address.setUser(user);

        return address;
    }

    @Override
    public UserRecord getUserById(Long id) throws UserNotFoundException {

        Optional<User> user =  userRepository.findById(id);

        if(user.isPresent())
         return  new UserRecord(user.get().getName(),
                        user.get().getAddress()
                                .stream().map(
                                      a->mapAddresstoAddressRecord(a))
                                    .collect(Collectors.toList()),
                        user.get().getPhoneNumber(),"");
        else {
            throw new UserNotFoundException(String.valueOf(id),false);
        }

    }

    /**
     * Used to mapAddressFromDbToAddressRecord
     * @param a
     * @return
     */
    private AddressRecord mapAddresstoAddressRecord(Address a) {

        return  new AddressRecord(a.getAddressType(),
                                    a.getHouseNumber(),
                                 a.getArea(),
                                a.getCity());
    }

    @Override
    public List<UserRecord> getAllUsers() {
        List<User> userList = userRepository.findAll();

        if(!userList.isEmpty()){
            return userList.stream().map( user -> mapUserToUserRecord(user)).collect(Collectors.toList());
        }else{
            return null;
        }

    }

    private UserRecord mapUserToUserRecord(User user) {
        return new UserRecord(user.getName(),
                user.getAddress()
                        .stream().map(
                                a->mapAddresstoAddressRecord(a))
                        .collect(Collectors.toList()),
                user.getPhoneNumber(),"");
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
            if(!userRepository.existsById(id))
                 throw new UserNotFoundException(String.valueOf(id),false);
            else{
                userRepository.deleteById(id);
            }
    }

    @Override
    public void updateUserById(Long id,UserRecord userRecord) throws UserNotFoundException {
        if(!userRepository.existsById(id))
            throw new UserNotFoundException(String.valueOf(id),false);
        else{
            Optional<User> user = userRepository.findById(id);
            user.get().setName(userRecord.name());
            user.get().setPhoneNumber(userRecord.phoneNumber());
            user.get().setPassword(userRecord.password());

            try {
                user.get().setAddress(userRecord.
                        address().
                        stream().
                        map(a -> mapAddresRecordToAddress(a,user.get()))
                        .collect(Collectors.toList()));

                userRepository.save(user.get());

            }catch (Exception e){
                throw new UpdateOperationFailedException("User Updation Process Failed : "+e.getMessage());
            }
        }
    }

    @Override
    public UserRecord getUserByName(String name) throws UserNotFoundException {
        Optional<User> user =  userRepository.findByName(name);

        if(user.isPresent())
            return  new UserRecord(user.get().getName(),
                    user.get().getAddress()
                            .stream().map(
                                    a->mapAddresstoAddressRecord(a))
                            .collect(Collectors.toList()),
                    user.get().getPhoneNumber(),"");
        else {
            throw new UserNotFoundException(String.valueOf(name),true);
        }
    }

    @Override
    public void deleteUserByName(String name) throws UserNotFoundException {
        if(!userRepository.existsByName(name))
            throw new UserNotFoundException(String.valueOf(name),true);
        else{
            userRepository.deleteByName(name);
        }
    }

    @Override
    public void updateUserByName(UserRecord userRecord) throws UserNotFoundException {
        if(!userRepository.existsByName(userRecord.name()))
            throw new UserNotFoundException(userRecord.name(),true);
        else{
            Optional<User> user = userRepository.findByName(userRecord.name());
            user.get().setPhoneNumber(userRecord.phoneNumber());
            user.get().setPassword(userRecord.password());

            try {
                user.get().setAddress(userRecord.
                        address().
                        stream().
                        map(a -> mapAddresRecordToAddress(a,user.get()))
                        .collect(Collectors.toList()));

                userRepository.save(user.get());

            }catch (Exception e){
                throw new UpdateOperationFailedException("User Updation Process Failed : "+e.getMessage());
            }
        }
    }
}
