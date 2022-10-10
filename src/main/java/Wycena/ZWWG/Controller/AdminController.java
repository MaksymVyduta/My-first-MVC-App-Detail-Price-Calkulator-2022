package Wycena.ZWWG.Controller;

import Wycena.ZWWG.Service.SecurityService;
import Wycena.ZWWG.Service.UserDetailMy;
import Wycena.ZWWG.Service.WycenaOring;
import Wycena.ZWWG.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    SecurityService SecurityService;
    @RequestMapping("/admindane" )
    public String a()  {
        return "admin" ;  }
    @GetMapping("/newuser")
    public String rejestruj(Model model) {
        model.addAttribute("user", new SecurityUser());
        return "NewUser";

    }

    @PostMapping("/dodaj")
    public String dodaj(SecurityUser securityUser) {
        SecurityService.addUser(securityUser);
        return "admin";
    }
}