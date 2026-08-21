package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.authentication.User;
import pl.publicprojects.jreservation.domain.reservation.ProductInfo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUsername(String name);
    Optional<User> getUserByEmail(String email);
}
