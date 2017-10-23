package pl.mqs.beword.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mqs.beword.db.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByFirstName(String firstName);
}
