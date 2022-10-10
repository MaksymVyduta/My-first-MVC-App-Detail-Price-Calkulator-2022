package Wycena.ZWWG.Controller;
import Wycena.ZWWG.model.Mieszanka;
import Wycena.ZWWG.model.MieszankaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype. Controller;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.* ;

import java.util.List;

@Controller
public class MieszankiController {
    private MieszankaRepo MieszankaRepo;
    @Autowired
    public void MieszankaController (MieszankaRepo MieszankaRepo)
    { this.MieszankaRepo = MieszankaRepo; }

    public List<Mieszanka> findAll() {
        return MieszankaRepo.findAll();
    }
    @RequestMapping("/nowamieszanka" )
    public Object Mieszanka()
    {  return "PobieranieMieszanka" ;  }


    @PostMapping ("/addmieszanka")
    public String mieszanka(@ModelAttribute Mieszanka PobieranieMieszanka, Model model){
        model.addAttribute("mieszanka", MieszankaRepo.save(PobieranieMieszanka));
        model.addAttribute( "WszystkieMieszanki", MieszankaRepo.findAll());
        return "Mieszanki";
    }


    @RequestMapping("/mieszanki" )
    public String pokaz( Model model){
        model.addAttribute( "WszystkieMieszanki", MieszankaRepo.findAll());
        return "Mieszanki";}


    @GetMapping("/kasuj" )

    public String kasuj ( Integer id, Model model){
        MieszankaRepo.deleteById(id);

        model.addAttribute( "WszystkieMieszanki", MieszankaRepo.findAll());
        return "Mieszanki";
}

    @RequestMapping("/wyszukajmieszanke")
    public String Wyszukajnazwa (@RequestParam ("nazwa") String nazwa, Model model){

        model.addAttribute( "WszystkieMieszanki", MieszankaRepo.findAllByNazwa(nazwa));
        return "Mieszanki";
    }

    @RequestMapping ("/przekieruj")
    public String przekieruj(Integer id, Model model)
    {   model.addAttribute("mieszanka", MieszankaRepo.findById(id));
        return "MieszankaAktualizuj";
    }

    @PostMapping ("/aktualizuj")
    public String aktualizujfirma( @ModelAttribute Mieszanka MieszankaAktualizuj, Model model){

        model.addAttribute("mieszanka",  MieszankaRepo.save(MieszankaAktualizuj));
        model.addAttribute( "WszystkieMieszanki", MieszankaRepo.findAll());

        return "Mieszanki";
    }



}