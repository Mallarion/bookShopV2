package by.klekto.bookSHopV2.controllers;

import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.repository.OrderRepository;
import by.klekto.bookSHopV2.repository.UserRepository;
import by.klekto.bookSHopV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Basket {

    @Autowired
    private UserService userService;

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;


    public Basket(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/basket")
    public String bascket1(Principal principal, Model model){
        String username= principal.getName();
        Order specificOrder= userService.findOrderByUserName(username);
        Iterable<BookInOrder> bookInOrders;
        if(specificOrder !=null){
         bookInOrders= specificOrder.getBookInOrders();
            model.addAttribute("zakaz", bookInOrders);
            return "basket";
        }
        else{
            model.addAttribute("Error", "Your basket is empty," +
                    " add something in it!");
            return "errors";
        }
        /*if (bookInOrders.iterator().hasNext()){
            System.out.println("We are find a book!!!!!");
            System.out.println(bookInOrders.iterator().next().getBook().);
        }*/

    }
}
