package by.klekto.bookSHopV2.controllers;
import by.klekto.bookSHopV2.domain.Book;
import by.klekto.bookSHopV2.domain.BookInOrder;
import by.klekto.bookSHopV2.domain.Order;
import by.klekto.bookSHopV2.domain.User;
import by.klekto.bookSHopV2.repository.BookRepository;
import by.klekto.bookSHopV2.repository.UserRepository;
import by.klekto.bookSHopV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String userList(Model model, Principal principal) {
        if(principal.getName().equals("Admin")){
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";}
        else{
            return "redirect:/login";
        }

    }

    @PostMapping("")
    public String  deleteShowUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
            return "redirect:/users";
        }

        else if(action.equals("show")){
            User user=  userRepository.findUserById(userId);
            String username= user.getUsername();
            Order specificOrder= userService.findOrderByUserName(username);
            Iterable<BookInOrder> bookInOrders;
            if(specificOrder !=null){
                bookInOrders= specificOrder.getBookInOrders();
                model.addAttribute("zakaz", bookInOrders);
                return "basket";
            }
            else{
                model.addAttribute("Error", "Basket of this user is empty!");
                return "errors";
            }
        }else
        {
            return "redirect:/users";
        }

    }

    @GetMapping("/addBook")
    public String addingBook(Principal principal) {
        if(principal.getName().equals("Admin")){
        return "addBook";}
        else{
            return "redirect:/login";
        }
    }

    @PostMapping("/addBook")
    public  String pokupka( @RequestParam(defaultValue = "null") String author,
                            @RequestParam(defaultValue = "null") String category,
                            @RequestParam(defaultValue = "0") Long cost,
                            @RequestParam(defaultValue = "null") String name,
                            @RequestParam(defaultValue = "0") int pages,
                            @RequestParam(defaultValue = "0") int year, Model model
    ){

    if (!author.equals("null") && !category.equals("null") && !name.equals("null") && cost!=0 && pages!=0 &&
            year!=0){
        Book book= new Book(name, author, category, year, pages, cost);
        bookRepository.save(book);
        return "redirect:/main";
    }else {
        model.addAttribute("Error", "You entered incorrect data!");
        return "errors";
    }

    }

    /*@GetMapping("/users/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }*/
}
