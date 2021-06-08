/*
package by.klekto.bookSHopV2.controllers;


import by.klekto.bookSHopV2.domain.User;
import by.klekto.bookSHopV2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;


    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String registration(){
        System.out.println("We are in getMapping method in login");
        return "login";}

    @PostMapping("/login")
    public String CheckUser(@RequestParam String username, @RequestParam String password, Model model) {
        System.out.println("We are in PostMapping method in login");
        User user= userRepository.findByUsername(username);
       if(user.getUsername().equals(username) && user.getPassword().equals(password)){
           model.addAttribute(user);
           System.out.println("All is okey");
           return "redirect:/main";}
        else if(user.getUsername().equals(username)){
           System.out.println("Not correct password!");
            model.addAttribute("message", "You should write correct password");
            return "login";
        }
        else{
           System.out.println("Neither password neither name are not correct!");
            return "redirect:/registration";}


    }
}
*/
