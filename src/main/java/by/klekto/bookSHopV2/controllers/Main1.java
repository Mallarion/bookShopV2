package by.klekto.bookSHopV2.controllers;


import by.klekto.bookSHopV2.domain.Book;
import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.domain.User;
import by.klekto.bookSHopV2.repository.BookInOrderRepository;
import by.klekto.bookSHopV2.repository.BookRepository;
import by.klekto.bookSHopV2.repository.OrderRepository;
import by.klekto.bookSHopV2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller

public class Main1 {

    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final BookInOrderRepository bookInOrderRepository;
    private final UserRepository userRepository;


    public Main1(BookRepository bookRepository, OrderRepository orderRepository, BookInOrderRepository bookInOrderRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.bookInOrderRepository = bookInOrderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/main") //функция выполнит  ся при переходе на главную страницу
    public String main1(Model model) {
        System.out.println("We are in bookrepository");
        Iterable<Book> books = bookRepository.findAllByOrderByIdDesc();
        if (books.iterator().hasNext()){
            System.out.println("We are find a book!!!!!");
            System.out.println(books.iterator().next().getName());
        }

        model.addAttribute("books", books);
        return "main1";
    }

    @GetMapping("/main/{id}")
    public String pokupka(@PathVariable("id") Long id,  Model model){
        Book book= bookRepository.findBookById(id);
        model.addAttribute("book", book);
        return "specific_book";
    }

    @PostMapping("/main/{id}")
    public  String pokupka(@PathVariable("id") Long id, @RequestParam int quantity
            , Principal principal){
        String username= principal.getName();
        User user= userRepository.findByUsername(username);
        Book book= bookRepository.findBookById(id);
        BookInOrder bookInOrder1 = new BookInOrder();
        bookInOrder1.setBook(book);


        if(quantity>0){
        bookInOrder1.setQuantity(quantity);
        }

        else{
            bookInOrder1.setQuantity(0);
        }

        List<BookInOrder > bookInOrders1= new ArrayList<>();



        Order order= orderRepository.findOrderByUserId(user.getId());
        if (order !=null){
            System.out.println("We update user@@@");
            List <BookInOrder> pastBookInOrders=order.getBookInOrders();
            bookInOrders1.addAll(pastBookInOrders);
            bookInOrder1.setOrder(order);
            bookInOrderRepository.save(bookInOrder1);
            bookInOrders1.add(bookInOrder1);
            order.setBookInOrders(bookInOrders1);
            orderRepository.save(order);



        }
        else{
            System.out.println("We are add new user!!!!");
            Order newOrder= new Order();
            bookInOrder1.setOrder(newOrder);
            bookInOrders1.add(bookInOrder1);
            newOrder.setUser(user);
            newOrder.setBookInOrders(bookInOrders1);
            orderRepository.save(newOrder);
            bookInOrderRepository.save(bookInOrder1);

        }

        /*bookInOrderRepository.save(bookInOrder1);*/
        return "redirect:/main";
    }
}
