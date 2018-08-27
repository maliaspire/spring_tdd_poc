package com.mxninja.example.spring_tdd_poc.repo;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.exception.NotValidException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@Component
public class UserDao {

    private final UserRepo USER_REPO;

    @Autowired
    public UserDao(UserRepo userRepo) {
        USER_REPO = userRepo;
    }

    public UserDomain save(UserDomain user) {
        if (StringUtils.isEmpty(user.getFirstName())) {
            throw new NullPointerException("first name should not be null");
        }
        if (StringUtils.isEmpty(user.getLastName())) {
            throw new NullPointerException("last name should not be null");
        }
        if (user.getAge() == null) {
            throw new NullPointerException("age should not be null");
        }
        if (user.getAge() == 0) {
            throw new RuntimeException("age should be greater than zero");
        }
        if (user.getEmails() == null) {
            throw new NullPointerException("emails collection should not be null");
        }
        if (user.getEmails().isEmpty()) {
            throw new RuntimeException("emails collection cannot be empty");
        }
        for (String email : user.getEmails()) {
            if (!EmailValidator.getInstance().isValid(email)) {
                throw new NotValidException("emails should be valid");
            }
        }
        if (user.getContactNumbers() == null) {
            throw new NullPointerException("contact number collection should not be null");
        }
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        return USER_REPO.save(user);
    }

    public List<UserDomain> findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new NullPointerException("email should not be null or empty");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new NotValidException("not valid email");
        }

        return USER_REPO.findByEmailsContainingIgnoreCase(email);
    }

    public Optional<UserDomain> findById(String uuidStr) {
        if (StringUtils.isEmpty(uuidStr)) {
            throw new NullPointerException("id should not be null or empty");
        }
        try {
            UUID uuid = UUID.fromString(uuidStr);
            return USER_REPO.findById(uuid);
        } catch (IllegalArgumentException ex) {
            throw new NotValidException("not valid uuid");
        }
    }

    public Optional<UserDomain> findById(UUID uuid) {
        if (uuid == null) {
            throw new NullPointerException("id should not be null or empty");
        }
        return USER_REPO.findById(uuid);
    }

    public List<UserDomain> findAll() {
        return USER_REPO.findAll();
    }

}
