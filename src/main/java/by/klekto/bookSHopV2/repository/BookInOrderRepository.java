package by.klekto.bookSHopV2.repository;

import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookInOrderRepository extends CrudRepository<BookInOrder, Long> {

    List<BookInOrder> findBooksInOrdersByOrder(Order order);
}
