package by.klekto.bookSHopV2.service;

import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.domain.Role;
import by.klekto.bookSHopV2.domain.User;
import by.klekto.bookSHopV2.repository.BookInOrderRepository;
import by.klekto.bookSHopV2.repository.OrderRepository;
import by.klekto.bookSHopV2.repository.RoleRepository;
import by.klekto.bookSHopV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookInOrderRepository bookInOrderRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public Order findOrderByUserName(String username){
        User user= userRepository.findByUsername(username);
        Order specificOrder= user.getOrder();
        return specificOrder;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            Order order= orderRepository.findOrderByUserId(userId);
            List <BookInOrder> bookInOrders= bookInOrderRepository.findBooksInOrdersByOrder(order);
            if(!bookInOrders.isEmpty()){
                bookInOrderRepository.deleteAll(bookInOrders);
                orderRepository.delete(order);
            }
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
