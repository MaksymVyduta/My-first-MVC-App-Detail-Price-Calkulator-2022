package Wycena.ZWWG.Controller;

import Wycena.ZWWG.model.Mieszanka;
import Wycena.ZWWG.model.MieszankaRepo;
import Wycena.ZWWG.model.WkladyMetalowe;
import Wycena.ZWWG.model.WkladyMetaloweRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ReactControllerWklady {
    private WkladyMetaloweRepo WkladyMetaloweRepo;
    @Autowired
    public void WkladyMetaloweRepoController (WkladyMetaloweRepo WkladyMetaloweRepo)
    { this.WkladyMetaloweRepo = WkladyMetaloweRepo; }

    @RequestMapping("/wklady" )
    public String pokaz( Model model){
        model.addAttribute( "WszystkieWklady", WkladyMetaloweRepo.findAll());
        return "Wklady" ;}

    @RequestMapping ("/aktualizujwklad")
    public String przekieruj(Integer id, Model model)
    {   model.addAttribute("wkladymetalowe", WkladyMetaloweRepo.findById(id));
        return "WkladAktualizuj";
    }
    @PostMapping ("/aktualizujcenewkladu")
    public String aktualizujwkl( @ModelAttribute WkladyMetalowe WkladAktualizuj, Model model){

        model.addAttribute("wkladymetalowe",  WkladyMetaloweRepo.save(WkladAktualizuj));
        model.addAttribute( "WszystkieWklady", WkladyMetaloweRepo.findAll());

        return "Wklady";
    }

}