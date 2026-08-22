package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.reservation.ProductInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, UUID> {
    Optional<ProductInfo> getProductByProductId(UUID id);
    @Query("SELECT product FROM ProductInfo product")
    List<ProductInfo> getProductsPage(Pageable pageable);
}
