package com.mxninja.example.spring_tdd_poc;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@SpringBootApplication
public class CommandLineApplication implements CommandLineRunner {

    private final UserDao userDao;

    @Autowired
    public CommandLineApplication(UserDao userDao) {
        this.userDao = userDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*UserDomain user = new UserDomain();
        user.setFirstName("Mx");
        user.setLastName("NINJA");
        user.setAge(28);
        user.setEmails(new ArrayList<>());
        user.getEmails().add("mxninja-@hotmail.com");
        user.setContactNumbers(new ArrayList<>());
        UserDomain savedUser = userDao.save(user);
        System.out.println(savedUser.getId());*/
    }
}
