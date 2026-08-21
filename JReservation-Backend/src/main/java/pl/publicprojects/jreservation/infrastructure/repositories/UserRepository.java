package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.authentication.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<User> getUserByUUID(UUID uuid);
}
