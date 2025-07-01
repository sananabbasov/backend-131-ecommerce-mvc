package az.edu.itbrains.fruitables.repositories;

import az.edu.itbrains.fruitables.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    List<Basket> findByUserId(Long userId);

    Basket findByProductId(Long productId);

    Basket findByProductIdAndUserId(Long productId, Long id);

    Basket findByIdAndUserId(Long cartId, Long id);
}
