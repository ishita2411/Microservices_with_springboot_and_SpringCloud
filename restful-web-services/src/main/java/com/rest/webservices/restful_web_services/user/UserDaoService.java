package com.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static Integer userCount = 0;

    static {
        users.add(new User(++userCount, "A", LocalDate.now().minusYears(10)));
        users.add(new User(++userCount, "B", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "C", LocalDate.now().minusYears(30)));

    }

    public List<User> findAll() {
        return users;
    }

    public User findUserBasedOnId(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);


    }

    public User saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;

    }

    public void deleteById(Integer id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
