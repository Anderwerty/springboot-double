package com.javarush.springbootdouble.repository;

import com.javarush.springbootdouble.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//FIRST
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Testcontainers
class UserRepositoryTest {

    private static final String MYSQL_IMAGE_NAME = "mysql";
    private static final String MYSQL_IMAGE_VERSION = "8.4.0";
    private static final String MYSQL_IMAGE = MYSQL_IMAGE_NAME + ":" + MYSQL_IMAGE_VERSION;
    @Autowired
    private UserRepository userRepository;


    //https://hub.docker.com/_/mysql
    @Container
    @ServiceConnection
    private static MySQLContainer mySQLContainer = new MySQLContainer(MYSQL_IMAGE);
//            .withPassword("serviceUser")
//            .withUsername("dbPassword")
//            .withDatabaseName("bank");

    //spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    //spring.datasource.url=jdbc:mysql://localhost:3306/bank
    //spring.datasource.username=serviceUser
    //spring.datasource.password=dbPassword

    // spring boot 3.0 -3.1(spring 6) no need, spring boot 2
    @DynamicPropertySource
    private static void initProperties(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        r.add("spring.datasource.username", mySQLContainer::getUsername);
        r.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
//    @Order(1)
    void saveShouldSaveItems1() {
        String email = "budanov@gmail.com";
        String firstname = "Kyrylo";

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname("Budanov");
        user.setPassword("bavovna");
        user.setEmail(email);

        userRepository.save(user);

        user.setPassword("12345");
        userRepository.save(user);

        User actual = userRepository.findByEmail(email).orElse(null);

        Assertions.assertEquals(actual.getEmail(), email);
        Assertions.assertEquals(actual.getFirstname(), firstname);
        Assertions.assertFalse(user == actual);
        Assertions.assertEquals(actual.getId(), user.getId());

        Assertions.assertEquals(userRepository.count(), 1);
    }

    @Test
    void saveShouldSaveItems2() {
        String email = "budanov2@gmail.com";
        String firstname = "Kyrylo";

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname("Budanov");
        user.setPassword("bavovna");
        user.setEmail(email);

        userRepository.save(user);

        Assertions.assertEquals(userRepository.count(), 1);
    }
}