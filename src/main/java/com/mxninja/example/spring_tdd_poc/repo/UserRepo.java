package com.mxninja.example.spring_tdd_poc.repo;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@Repository
interface UserRepo extends MongoRepository<UserDomain, UUID> {

    UserDomain findByEmailsContainingIgnoreCase(String email);

}
