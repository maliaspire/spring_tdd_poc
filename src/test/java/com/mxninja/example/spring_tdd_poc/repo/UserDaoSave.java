package com.mxninja.example.spring_tdd_poc.repo;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.exception.NotValidException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@RunWith(SpringRunner.class)
public class UserDaoSave {

    @TestConfiguration
    static class Config {

        @MockBean
        private UserRepo userRepo;

        @Bean
        public UserDao employeeService() {
            return new UserDao(userRepo);
        }
    }

    @Autowired
    private UserDao userDao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenSaveWithNullFirstNameThrowNullException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("first name should not be null");
        UserDomain userDomain = new UserDomain();
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithNullLastNameThrowNullException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("last name should not be null");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithNullAgeThrowNullException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("age should not be null");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithZeroAgeThrowRunTimeException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("age should be greater than zero");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDomain.setAge(0);
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithNullEmailsThrowNullException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("emails collection should not be null");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDomain.setAge(28);
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithEmptyEmailsThrowRunTimeException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("emails collection cannot be empty");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDomain.setAge(28);
        userDomain.setEmails(new ArrayList<>());
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithNotValidEmailsThrowNotValidException() {
        exception.expect(NotValidException.class);
        exception.expectMessage("emails should be valid");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDomain.setAge(28);
        userDomain.setEmails(new ArrayList<>());
        userDomain.getEmails().add("randomString");
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveWithNullContactNumberThrowNullException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("contact number collection should not be null");
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDomain.setAge(28);
        userDomain.setEmails(new ArrayList<>());
        userDomain.getEmails().add("mxninja-@hotmail.com");
        userDao.save(userDomain);
    }

    @Test
    public void whenSaveReturnDifferentReference() {
        UserDomain userDomain = new UserDomain();
        userDomain.setFirstName("Mohammad");
        userDomain.setLastName("Ali");
        userDomain.setAge(28);
        userDomain.setEmails(new ArrayList<>());
        userDomain.getEmails().add("mxninja-@hotmail.com");
        userDomain.setContactNumbers(new ArrayList<>());
        assertNotSame(userDomain, userDao.save(userDomain));
    }

}
