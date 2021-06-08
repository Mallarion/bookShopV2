package by.klekto.bookSHopV2.repository;

import by.klekto.bookSHopV2.domain.Book;
import by.klekto.bookSHopV2.domain.BookInOrder;
import org.springframework.data.repository.CrudRepository;

public interface BookInOrderRepository extends CrudRepository<BookInOrder, Long> {
}
