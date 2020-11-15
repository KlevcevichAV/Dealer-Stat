package com.dealerstat.service;

import com.dealerstat.entity.profile.User;
import com.dealerstat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl {
    private final String PREFIX = "ROLE_";
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAllByApprovedAndRole(true, "DEALER");
    }

    public User getUser(int id) {
        return userRepository.findByIdAndRoleAndApproved(id, "DEALER", true);
    }

    public void edit(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail());
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        if (userFromDB != null) {
            userRepository.save(user);
            return;
        }
    }

    public boolean registration(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail());

        if (Objects.nonNull(userFromDB)) {
            return false;
        }

        if (Objects.isNull(user.getFirstName()) || Objects.isNull(user.getLastName())){
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        user.setCreatedAt(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + '-' + calendar.get(Calendar.DAY_OF_MONTH));
        user.setRole(PREFIX + "DEALER");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

}
