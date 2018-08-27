package com.mxninja.example.spring_tdd_poc.repo;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.exception.NotValidException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@RunWith(SpringRunner.class)
public class UserDaoQueryTest {

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

    @Before
    public void initUsers() {
        UserDomain user = new UserDomain();
        user.setFirstName("Mx");
        user.setLastName("NINJA");
        user.setAge(28);
        user.setEmails(new ArrayList<>());
        user.getEmails().add("mxninja-@hotmail.com");
        user.setContactNumbers(new ArrayList<>());

        Mockito.when(userDao.findByEmail("mxninja-@hotmail.com")).thenReturn(user);
        Mockito.when(userDao.findById("3a3bf002-f348-4a82-8c24-91af7ffa61a2")).thenReturn(Optional.of(user));
    }

    @Test
    public void whenFindByEmailWithNullEmailThrowNullException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("email should not be null or empty");
        userDao.findByEmail(null);
    }

    @Test
    public void whenFindByEmailWithNotValidEmailThrowNotValidException() {
        exception.expect(NotValidException.class);
        exception.expectMessage("not valid email");
        userDao.findByEmail("randomString");
    }

    @Test
    public void whenFindByEmailWithExistEmailReturnData() {
        UserDomain userDomain = userDao.findByEmail("mxninja-@hotmail.com");
        Assert.assertNotNull(userDomain);
    }

    @Test
    public void whenFindByEmailWithNotExistEmailReturnNull() {
        Assert.assertNull(userDao.findByEmail("mxninja@hotmail.com"));
    }

    @Test
    public void whenFindByIdWithNullOrEmptyIdReturnNullPointerException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("id should not be null or empty");
        userDao.findById("");
    }

    @Test
    public void whenFindByIdWithNotValidIdReturnNotValidException() {
        exception.expect(NotValidException.class);
        exception.expectMessage("not valid uuid");
        userDao.findById("randomString");
    }

    @Test
    public void whenFindByIdWithValidIdReturnData() {
        Assert.assertTrue(userDao.findById("3a3bf002-f348-4a82-8c24-91af7ffa61a2").isPresent());
    }

    @Test
    public void whenFindByIdWithValidIdReturnNull() {
        Assert.assertFalse(userDao.findById(UUID.fromString("4a3bf002-f348-4a82-8c24-91af7ffa61a2")).isPresent());
    }

}
