package com.SYT.STY_project.repository;
import com.SYT.STY_project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String username);
    User findByMail(String mail);

    User save(User user);

    User getUserByMail(String mail);
}

