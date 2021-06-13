package by.klekto.bookSHopV2.repository;

import by.klekto.bookSHopV2.domain.Book;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findOrderByUserId(Long id);
    Order findOrderByUser(User user);


}
