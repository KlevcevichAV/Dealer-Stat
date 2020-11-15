package com.dealerstat.repository;

import com.dealerstat.entity.profile.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAllByApprovedAndRole(boolean approved, String role);

    User findByIdAndRoleAndApproved(int id, String role, boolean approved);

    User findByEmail(String email);

    User findById(int id);
}
