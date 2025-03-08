package com.interview.prep.records;

import java.util.List;

public record UserRecord(String name, List<AddressRecord> address, String phoneNumber,String password) {
}
