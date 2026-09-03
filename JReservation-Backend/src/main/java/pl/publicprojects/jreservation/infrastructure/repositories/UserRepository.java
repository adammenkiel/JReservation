package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUsername(String name);
    Optional<User> getUserByEmail(String email);
}
