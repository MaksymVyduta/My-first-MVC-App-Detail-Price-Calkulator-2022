package Wycena.ZWWG.Controller;


import Wycena.ZWWG.model.ObrotNaPrasie;
import Wycena.ZWWG.model.ObrotNaPrasieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ObrotNaPrasieController {
    private ObrotNaPrasieRepo ObrotNaPrasieRepo;
    @Autowired
    public void ObrotNaPrasieController (ObrotNaPrasieRepo ObrotNaPrasieRepo)
    { this.ObrotNaPrasieRepo = ObrotNaPrasieRepo; }

    @RequestMapping("/obrot" )
    public String pokaz( Model model )
        throws Exception
    {   model.addAttribute( "obrotNaPrasie", ObrotNaPrasieRepo.findById(1));
        return "ObrotNaPrasie";
    }

    @RequestMapping("/aktualizujobr")
    public String aktualizobr( @ModelAttribute ObrotNaPrasie obrotNaPrasie2, Model model )
            throws Exception
    {   model.addAttribute( "obrotNaPrasie", ObrotNaPrasieRepo.save(obrotNaPrasie2));
        System.out.println(obrotNaPrasie2);
        model.addAttribute( "obrotNaPrasie", ObrotNaPrasieRepo.findById(1));
        return "ObrotNaPrasie";
    }

    }

