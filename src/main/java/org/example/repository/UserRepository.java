package org.example.repository;

import org.example.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    @Query("select u from User u where u.login like concat(:prefix, '%')")
    Optional<List<User>> findByLoginLike( @Param("prefix")  String prefix);

    Optional<List<User>> findAllByBirthDateGreaterThan(LocalDate date);



}
