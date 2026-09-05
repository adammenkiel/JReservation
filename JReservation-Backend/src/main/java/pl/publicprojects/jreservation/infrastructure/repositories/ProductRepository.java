package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.product.ProductInfo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, UUID> {
    Optional<ProductInfo> getProductByProductId(UUID id);
    @Query("SELECT prod FROM ProductInfo prod WHERE " +
            "prod.starts < :dateNow AND prod.ends > :dateNow AND prod.amount > 0")
    List<ProductInfo> getAvailableProductsPage(
            @Param("dateNow") LocalDateTime date,
            Pageable pageable
    );
}
