package by.klekto.bookSHopV2.controllers;


import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.domain.User;
import by.klekto.bookSHopV2.repository.OrderRepository;
import by.klekto.bookSHopV2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Basket {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;


    public Basket(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/basket")
    public String bascket1(Principal principal, Model model){
        String username= principal.getName();
        User user= userRepository.findByUsername(username);
        Order specificOrder= user.getOrder();
        Iterable<BookInOrder> bookInOrders= specificOrder.getBookInOrders();
        /*if (bookInOrders.iterator().hasNext()){
            System.out.println("We are find a book!!!!!");
            System.out.println(bookInOrders.iterator().next().getBook().);
        }*/
        model.addAttribute("zakaz", bookInOrders);
        return "basket";
    }
}
