package Wycena.ZWWG.Controller;
import Wycena.ZWWG.Service.*;
import Wycena.ZWWG.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class WycenaController {

    private Wycena.ZWWG.model.MieszankaRepo MieszankaRepo;

    @Autowired
    public void MieszankaController(MieszankaRepo MieszankaRepo) {
        this.MieszankaRepo = MieszankaRepo;
    }

    private Wycena.ZWWG.model.ObrotNaPrasieRepo ObrotNaPrasieRepo;

    @Autowired
    public void ObrotNaPrasieController(ObrotNaPrasieRepo ObrotNaPrasieRepo) {
        this.ObrotNaPrasieRepo = ObrotNaPrasieRepo;
    }

    private Wycena.ZWWG.model.WycenioneWyrobyRepo WycenioneWyrobyRepo;

    @Autowired
    public void WycenioneWyroby(WycenioneWyrobyRepo WycenioneWyrobyRepo) {
        this.WycenioneWyrobyRepo = WycenioneWyrobyRepo;
    }
    private  Wycena.ZWWG.model.WkladyMetaloweRepo WkladyMetaloweRepo;
    @Autowired
    public void WkladyMetaloweRepoController (WkladyMetaloweRepo WkladyMetaloweRepo)
    { this.WkladyMetaloweRepo = WkladyMetaloweRepo; }


    private EmailSender emailSender;

    @Autowired
    public void Send(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RequestMapping("/wycena")
    public String Wycena() {
        return "Wycena";
    }


    @RequestMapping("/simmerwycena")
    public String pokazac1(Model model) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        return "SimmerWycena";
    }

    @RequestMapping("/cenaSimmer")

    public String d(Principal principal, Model model, @RequestParam int id, int marza, double srednicawewn, double srednicazewn, double wysokosc, double iloscsztuk) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        String wymiar= srednicawewn+"x"+srednicazewn+"x"+wysokosc;
        Mieszanka material = MieszankaRepo.getById(id);
        ObrotNaPrasie obr = ObrotNaPrasieRepo.getById(1);
        double iloscpodwojna = iloscsztuk * 2;
        double iloscpiec = iloscsztuk * 5;
        double cenaWkladu = WkladySimmer.WkladySimmering(srednicazewn);
        double cenaSimmer = WycenaSimmering.WycenaSimmering(material.getNazwa(), cenaWkladu, obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscsztuk, marza);
        double cenaSimmer2 = WycenaSimmering.WycenaSimmering(material.getNazwa(), cenaWkladu, obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpodwojna, marza);
        double cenaSimmer3 = WycenaSimmering.WycenaSimmering(material.getNazwa(), cenaWkladu, obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpiec, marza);
       WycenioneWyroby Simmeing =new WycenioneWyroby(1,"Simmering", material ,iloscsztuk, WkladyMetaloweRepo.findBycenaWkladuAndtp(cenaWkladu,"S"), cenaSimmer,wymiar );
        WycenioneWyroby Simmeing2 =new WycenioneWyroby(2,"Simmering", material ,iloscpodwojna, WkladyMetaloweRepo.findBycenaWkladuAndtp(cenaWkladu,"S"), cenaSimmer2,wymiar );
        WycenioneWyroby Simmeing3 =new WycenioneWyroby(3,"Simmering", material ,iloscpiec, WkladyMetaloweRepo.findBycenaWkladuAndtp(cenaWkladu,"S"), cenaSimmer3,wymiar );
        WycenioneWyrobyRepo.save(Simmeing);
        WycenioneWyrobyRepo.save(Simmeing2);
        WycenioneWyrobyRepo.save(Simmeing3);
        model.addAttribute("wyrob1",WycenioneWyrobyRepo.findById(1));
        model.addAttribute("wyrob2",WycenioneWyrobyRepo.findById(2));
        model.addAttribute("wyrob3",WycenioneWyrobyRepo.findById(3));
        model.addAttribute("material1",MieszankaRepo.getById(id));
         String zalogowany = principal.getName().toString();
        String wycenaSimm = "Cena dla wyrobu typu Simmering  "+" " +srednicawewn + " x "+ srednicazewn+ " x "+ wysokosc+ "  "+ "  z materiału  "+ material.getNazwa() + "  " +material.getKolor() + " "+ "dla ilości  " + iloscsztuk + " "+ "  sztuk  WYNOSI   " +" "+ cenaSimmer+" "+ " PLN netto/szt " +
        " "+ " Można też zaproponować klientowi cenę " + " "+ cenaSimmer2 +" "+ " PLN netto/szt " + " "+ "przy zamówieniu "  + " " + iloscpodwojna+ " sztuk" + " "+"lub cenę " + " "+ cenaSimmer3 +" "+ " PLN netto/szt " + " "+"przy zamówieniu "+ " "+ iloscpiec+ " sztuk" ;
           try {
              emailSender.send( zalogowany , "Nowa wycena Simmeringa" +" " +srednicawewn + "x"+ srednicazewn+ "x"+ wysokosc,wycenaSimm );
            } catch (Exception e) {
              e.printStackTrace();
            }


        return "Postwycena";


    }

    @RequestMapping("/oringwycena")
    public String pokazac2(Model model) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        return "OringWycena";
    }

    @RequestMapping("/cenaOring")
    public String b(Principal principal, Model model, @RequestParam int id, int marza, double srednicawewn, double przekroj, double iloscsztuk) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        String wymiar= srednicawewn+"x"+przekroj;

        Mieszanka material = MieszankaRepo.getById(id);
        ObrotNaPrasie obr = ObrotNaPrasieRepo.getById(1);
        double iloscpodwojna = iloscsztuk * 2;
        double iloscpiec = iloscsztuk * 5;
        double cenaOringa = WycenaOring.WycenaOring(obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, przekroj, iloscsztuk, marza);

        double cenaOringa2 = WycenaOring.WycenaOring(obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, przekroj, iloscpodwojna, marza);

        double cenaOringa3 = WycenaOring.WycenaOring(obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, przekroj, iloscpiec, marza);

      WycenioneWyroby Oring =new WycenioneWyroby(1,"Oring", material ,iloscsztuk,cenaOringa,wymiar );
        WycenioneWyroby Oring2 =new WycenioneWyroby(2,"Oring", material ,iloscpodwojna,cenaOringa2,wymiar  );
        WycenioneWyroby Oring3 =new WycenioneWyroby(3,"Oring", material ,iloscpiec,cenaOringa3,wymiar  );
         WycenioneWyrobyRepo.save(Oring);
        WycenioneWyrobyRepo.save(Oring2);
        WycenioneWyrobyRepo.save(Oring3);
        model.addAttribute("wyrob1",WycenioneWyrobyRepo.findById(1));
        model.addAttribute("wyrob2",WycenioneWyrobyRepo.findById(2));
        model.addAttribute("wyrob3",WycenioneWyrobyRepo.findById(3));
        model.addAttribute("material1",MieszankaRepo.getById(id));
  String wycenaOring = "Cena dla wyrobu typu O-ring  " + " " + srednicawewn + " x " + przekroj + " " + "  z materiału  " + material.getNazwa() + "  " + material.getKolor() + " " + "dla ilości  " + iloscsztuk + " " + "  sztuk  WYNOSI   " + " " + cenaOringa+" "+ " PLN netto/szt " +
      " " + " Można też zaproponować klientowi cenę " + " " + cenaOringa2 +" "+ " PLN netto/szt " + " " + "przy zamówieniu " + " " + iloscpodwojna+ " sztuk"+
          " " + " lub cenę " + " " + cenaOringa3 +" "+ " PLN netto/szt " + " " + "przy zamówieniu " + " " + iloscpiec+ " sztuk";

        String zalogowany = principal.getName().toString();
       try {
           emailSender.send(zalogowany, "Wyceniono nowego O-ringa"+" " +srednicawewn + "x"+ przekroj, wycenaOring);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return "Postwycena";


    }

    @RequestMapping("/Zwycena")
    public String pokazac3(Model model) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        return "Zwycena";
    }

    @RequestMapping("/cenaz")
    public String b(Principal principal, Model model, @RequestParam int id, int marza, double srednicawewn, double srednicazewn, double wysokosc, double iloscsztuk) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        String wymiar= srednicawewn+"x"+srednicazewn+"x"+wysokosc;
        Mieszanka material = MieszankaRepo.getById(id);
        ObrotNaPrasie obr = ObrotNaPrasieRepo.getById(1);
        double iloscpodwojna = iloscsztuk * 2;
        double iloscpiec = iloscsztuk * 5;
        double cenaZ = WycenaZ.WycenaZ( obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscsztuk, marza);
        double cenaZ2 = WycenaZ.WycenaZ( obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpodwojna, marza);
        double cenaZ3 = WycenaZ.WycenaZ( obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpiec, marza);
        WycenioneWyroby Z =new WycenioneWyroby(1,"Z", material ,iloscsztuk, cenaZ, wymiar );
        WycenioneWyroby Z2 =new WycenioneWyroby(2,"Z", material ,iloscpodwojna, cenaZ2, wymiar );
        WycenioneWyroby Z3 =new WycenioneWyroby(3,"Z", material ,iloscpiec, cenaZ3, wymiar );
        WycenioneWyrobyRepo.save(Z);
        WycenioneWyrobyRepo.save(Z2);
        WycenioneWyrobyRepo.save(Z3);
        model.addAttribute("wyrob1",WycenioneWyrobyRepo.findById(1));
        model.addAttribute("wyrob2",WycenioneWyrobyRepo.findById(2));
        model.addAttribute("wyrob3",WycenioneWyrobyRepo.findById(3));
        model.addAttribute("material1",MieszankaRepo.getById(id));
        String zalogowany = principal.getName().toString();
        String wycenaZ = "Cena dla zgarniacza typu Z  "+" " +srednicawewn + " x "+ srednicazewn+ " x "+ wysokosc+ "  "+ " z materiału "+ material.getNazwa() + "  " +material.getKolor() + " "+ "dla ilości  " + iloscsztuk + " "+ "  sztuk  WYNOSI   " +" "+ cenaZ+" "+ " PLN netto/szt " +
                " "+ " Można też zaproponować klientowi cenę " + " "+ cenaZ2+" "+ " PLN netto/szt "  + " "+ "przy zamówieniu "  + " " + iloscpodwojna+ " sztuk" + " "+"lub cenę " + " "+ cenaZ3+" "+ " PLN netto/szt "  + " "+"przy zamówieniu "+ " "+ iloscpiec+ " sztuk" ;
        try {
            emailSender.send( zalogowany , "Nowa wycena zgarniacza Z"+" " +srednicawewn + "x"+ srednicazewn+ "x"+ wysokosc,wycenaZ );
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "Postwycena";


    }
    @RequestMapping("/Uwycena")
    public String pokazac4(Model model) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        return "Uwycena";
    }
    @RequestMapping("/cenau")
    public String c(Principal principal, Model model, @RequestParam int id, int marza, double srednicawewn, double srednicazewn, double wysokosc, double iloscsztuk) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        String wymiar= srednicawewn+"x"+srednicazewn+"x"+wysokosc;
        Mieszanka material = MieszankaRepo.getById(id);
        ObrotNaPrasie obr = ObrotNaPrasieRepo.getById(1);
        double iloscpodwojna = iloscsztuk * 2;
        double iloscpiec = iloscsztuk * 5;
        double cenaU = WycenaZ.WycenaZ( obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscsztuk, marza);
        double cenaU2 = WycenaZ.WycenaZ( obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpodwojna, marza);
        double cenaU3 = WycenaZ.WycenaZ( obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpiec, marza);
        WycenioneWyroby U =new WycenioneWyroby(1," U ", material ,iloscsztuk, cenaU,wymiar );
        WycenioneWyroby U2 =new WycenioneWyroby(2," U ", material ,iloscpodwojna, cenaU2,wymiar  );
        WycenioneWyroby U3 =new WycenioneWyroby(3," U", material ,iloscpiec, cenaU3,wymiar  );
        WycenioneWyrobyRepo.save(U);
        WycenioneWyrobyRepo.save(U2);
        WycenioneWyrobyRepo.save(U3);
        model.addAttribute("wyrob1",WycenioneWyrobyRepo.findById(1));
        model.addAttribute("wyrob2",WycenioneWyrobyRepo.findById(2));
        model.addAttribute("wyrob3",WycenioneWyrobyRepo.findById(3));
        model.addAttribute("material1",MieszankaRepo.getById(id));
        String zalogowany = principal.getName().toString();
        String wycenaU = "Cena dla  uszczelki typu U  "+" " +srednicawewn + " x "+ srednicazewn+ " x "+ wysokosc+ "  "+ "  z materiału  "+ material.getNazwa() + "  " +material.getKolor() + " "+ "dla ilości  " + iloscsztuk + " "+ "  sztuk  WYNOSI   " +" "+ cenaU+" "+ " PLN netto/szt " +
                " "+ " Można też zaproponować klientowi cenę " + " "+ cenaU2+" "+ " PLN netto/szt "  + " "+ "przy zamówieniu "  + " " + iloscpodwojna + " sztuk"+ " "+"lub cenę " + " "+ cenaU3+" "+ " PLN netto/szt "  + " "+"przy zamówieniu "+ " "+ iloscpiec + " sztuk";
        try {
            emailSender.send( zalogowany , "Nowa wycena uszczelki typu U"+" " +srednicawewn + "x"+ srednicazewn+ "x"+ wysokosc,wycenaU );
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "Postwycena";

}
    @RequestMapping("/ZZwycena")
    public String pokazac5(Model model) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        return "ZZwycena";
    }

    @RequestMapping("/cenaZZ")

    public String zz(Principal principal, Model model, @RequestParam int id, int marza, double srednicawewn, double srednicazewn, double wysokosc, double iloscsztuk) {
        model.addAttribute("WszystkieMieszanki", MieszankaRepo.findAll());
        String wymiar= srednicawewn+"x"+srednicazewn+"x"+wysokosc;
        Mieszanka material = MieszankaRepo.getById(id);
        ObrotNaPrasie obr = ObrotNaPrasieRepo.getById(1);
        double iloscpodwojna = iloscsztuk * 2;
        double iloscpiec = iloscsztuk * 5;
        double cenaWkladu = WkladyZZ.WkladyZZ(srednicazewn);
        double cenaZZ = WycenaZZ.WycenaZZ(material.getNazwa(), cenaWkladu, obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscsztuk, marza);
        double cenaZZ2 = WycenaZZ.WycenaZZ(material.getNazwa(), cenaWkladu, obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpodwojna, marza);
        double cenaZZ3 = WycenaZZ.WycenaZZ(material.getNazwa(), cenaWkladu, obr.getObrotNaPrasie(), material.getCena(), material.getGestosc(), srednicawewn, srednicazewn, wysokosc, iloscpiec, marza);
        WycenioneWyroby ZZ =new WycenioneWyroby(1,"ZZ", material ,iloscsztuk, WkladyMetaloweRepo.findBycenaWkladuAndtp(cenaWkladu,"ZZ"), cenaZZ,wymiar );
        WycenioneWyroby ZZ2 =new WycenioneWyroby(2,"ZZ", material ,iloscpodwojna, WkladyMetaloweRepo.findBycenaWkladuAndtp(cenaWkladu,"ZZ"), cenaZZ2,wymiar );
        WycenioneWyroby ZZ3 =new WycenioneWyroby(3,"ZZ", material ,iloscpiec, WkladyMetaloweRepo.findBycenaWkladuAndtp(cenaWkladu,"ZZ"), cenaZZ3,wymiar );
        WycenioneWyrobyRepo.save(ZZ);
        WycenioneWyrobyRepo.save(ZZ2);
        WycenioneWyrobyRepo.save(ZZ3);
        model.addAttribute("wyrob1",WycenioneWyrobyRepo.findById(1));
        model.addAttribute("wyrob2",WycenioneWyrobyRepo.findById(2));
        model.addAttribute("wyrob3",WycenioneWyrobyRepo.findById(3));
        model.addAttribute("material1",MieszankaRepo.getById(id));
        String zalogowany = principal.getName().toString();
        String wycenaZZ = "Cena dla zgarniacza typu ZZ  "+" " +srednicawewn + " x "+ srednicazewn+ " x "+ wysokosc+ "  "+ "  z materiału "+ material.getNazwa() + "  " +material.getKolor() + " "+ "dla ilości  " + iloscsztuk + " "+ "  sztuk  WYNOSI   " +" "+ cenaZZ+" "+ " PLN netto/szt " +
                " "+ " Można też zaproponować klientowi cenę " + " "+ cenaZZ2+" "+ " PLN netto/szt "  + " "+ "przy zamówieniu "  + " " + iloscpodwojna+ " sztuk" + " "+"lub cenę " + " "+ cenaZZ3+" "+ " PLN netto/szt "  + " "+"przy zamówieniu "+ " "+ iloscpiec+ " sztuk" ;
        try {
            emailSender.send( zalogowany , "Nowa wycena zgarniacza ZZ"+" " +srednicawewn + "x"+ srednicazewn+ "x"+ wysokosc,wycenaZZ );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Postwycena";


    }
}