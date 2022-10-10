package Wycena.ZWWG.Service;

import Wycena.ZWWG.model.WkladyMetaloweRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WkladyZZ {

    private static Wycena.ZWWG.model.WkladyMetaloweRepo WkladyMetaloweRepo;
    @Autowired
    public void WkladyMetaloweRepoController (Wycena.ZWWG.model.WkladyMetaloweRepo WkladyMetaloweRepo)
    { this.WkladyMetaloweRepo = WkladyMetaloweRepo; }
    public static double WkladyZZ(double srednicazewn)
    { double cenaWkladu = 0;
        if (srednicazewn<=29)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(23).getCenaWkladu();
        }
        else if (srednicazewn>29&& srednicazewn<=39)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(24).getCenaWkladu();
        }
        else if (srednicazewn>39&& srednicazewn<=49)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(25).getCenaWkladu();
        }
        else if (srednicazewn>49&& srednicazewn<=59)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(26).getCenaWkladu();
        }
        else if (srednicazewn>59&& srednicazewn<=69)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(27).getCenaWkladu();
        }
        else if (srednicazewn>69&& srednicazewn<=79)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(28).getCenaWkladu();
        }
        else if (srednicazewn>79&& srednicazewn<=89)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(29).getCenaWkladu();
        }
        else if (srednicazewn>89&& srednicazewn<=99)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(30).getCenaWkladu();
        }
        else if (srednicazewn>99&& srednicazewn<=109)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(31).getCenaWkladu();
        }
        else if (srednicazewn>109&& srednicazewn<=129)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(32).getCenaWkladu();
        }
        else
        {
            cenaWkladu=100;
        }
        return cenaWkladu ;}

}
