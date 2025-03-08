package com.interview.prep.records;

import com.interview.prep.utility.Role;

import java.util.List;
import java.util.Set;

public record UserRecord(String name, List<AddressRecord> address,
                         String phoneNumber, String password, Set<Role> roles) {
}
