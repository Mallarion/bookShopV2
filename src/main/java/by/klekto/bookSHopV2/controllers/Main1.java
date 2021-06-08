package by.klekto.bookSHopV2.controllers;


import by.klekto.bookSHopV2.domain.Book;
import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.repository.BookInOrderRepository;
import by.klekto.bookSHopV2.repository.BookRepository;
import by.klekto.bookSHopV2.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller

public class Main1 {

    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final BookInOrderRepository bookInOrderRepository;


    public Main1(BookRepository bookRepository, OrderRepository orderRepository, BookInOrderRepository bookInOrderRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.bookInOrderRepository = bookInOrderRepository;
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
    public String pokupka(@PathVariable("id") Long id, Model model){
        Book book= bookRepository.findBookById(id);
        model.addAttribute("book", book);
        return "specific_book";
    }

    /*@PostMapping("/main/{id}")
    public  String pokupka(@PathVariable("id") Long id, @RequestParam int quantity
            , Model model){
        Book book= bookRepository.findBookById(id);
        BookInOrder bookInOrder1 = new BookInOrder();
        bookInOrder1.setBook(book);
        if(quantity>0){
        bookInOrder1.setQuantity(quantity);
        }

        else{
            bookInOrder1.setQuantity(0);
        }

        bookInOrderRepository.save(bookInOrder1);
        Order order= orderRepository.findOrderByUserId(id);
        if (order !=null){
            List<BookInOrder > bookInOrders= new ArrayList<>();
            bookInOrders.add(bookInOrder1);
            order.setBookInOrders(bookInOrders);
            orderRepository.save(order);

        }

        if()

        return "specific_book";
    }*/
}
