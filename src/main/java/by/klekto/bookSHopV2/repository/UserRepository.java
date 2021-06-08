package by.klekto.bookSHopV2.repository;


import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
