package com.interview.prep.repoTest;

import com.interview.prep.entities.Address;
import com.interview.prep.entities.User;
import com.interview.prep.repositories.UserRepository;
import com.interview.prep.utility.AddressType;
import com.interview.prep.utility.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class userRepoTest {

    @Autowired
    UserRepository userRepo;

    Long userId = -1L;



    public User getUser(){
        User user = new User();
        user.setName("TestUser");
        user.setPassword("TestPassword");
        user.setPhoneNumber("91406101819");

        Address address = new Address();
        address.setUser(user);
        address.setCity("testCity");
        address.setArea("testArea");
        address.setHouseNumber("testNumber");
        address.setAddressType(AddressType.HOME);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        roles.add(Role.ROLE_ADMIN);

        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);

        user.setAddress(addresses);

        user.setRoles(roles);

        return  user;

    }

    @Test
    @Transactional
    public void testUserCreation(){

        User userInDb = userRepo.save(getUser());
        Optional<User> user2 = userRepo.findById(userInDb.getId());
        Assertions.assertEquals(user2.get().getName(),"TestUser");
        Assertions.assertEquals(user2.get().getAddress().get(0).getArea(),"testArea");

    }
}
