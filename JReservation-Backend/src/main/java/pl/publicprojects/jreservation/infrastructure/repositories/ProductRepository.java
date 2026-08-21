package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.reservation.ProductInfo;

import java.util.Optional;

@Repository
public interface ProductRepository {
    Optional<ProductInfo> getProductById(int id);
}
