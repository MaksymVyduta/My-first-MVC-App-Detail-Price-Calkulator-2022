package Wycena.ZWWG.Service;

import Wycena.ZWWG.model.WkladyMetaloweRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WkladySimmer {
    private static Wycena.ZWWG.model.WkladyMetaloweRepo WkladyMetaloweRepo;
    @Autowired
    public void WkladyMetaloweRepoController (WkladyMetaloweRepo WkladyMetaloweRepo)
    { this.WkladyMetaloweRepo = WkladyMetaloweRepo; }
    public static double WkladySimmering(double srednicazewn)
    { double cenaWkladu = 0;
        if (srednicazewn<=29)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(1).getCenaWkladu();
        }
        else if (srednicazewn>29&& srednicazewn<=39)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(2).getCenaWkladu();
        }
        else if (srednicazewn>39&& srednicazewn<=49)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(3).getCenaWkladu();
        }
        else if (srednicazewn>49&& srednicazewn<=59)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(4).getCenaWkladu();
        }
        else if (srednicazewn>59&& srednicazewn<=69)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(5).getCenaWkladu();
        }
        else if (srednicazewn>69&& srednicazewn<=79)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(6).getCenaWkladu();
        }
        else if (srednicazewn>79&& srednicazewn<=89)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(7).getCenaWkladu();
        }
        else if (srednicazewn>89&& srednicazewn<=99)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(8).getCenaWkladu();
        }
        else if (srednicazewn>99&& srednicazewn<=109)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(9).getCenaWkladu();
        }
        else if (srednicazewn>109&& srednicazewn<=129)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(10).getCenaWkladu();
        }
        else if (srednicazewn>129&& srednicazewn<=149)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(11).getCenaWkladu();
        }
        else if (srednicazewn>149&& srednicazewn<=179)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(12).getCenaWkladu();
        }
        else if (srednicazewn>179&& srednicazewn<=209)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(13).getCenaWkladu();
        }
        else if (srednicazewn>209&& srednicazewn<=239)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(14).getCenaWkladu();
        }
        else if (srednicazewn>239&& srednicazewn<=269)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(15).getCenaWkladu();
        }
        else if (srednicazewn>269&& srednicazewn<=299)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(16).getCenaWkladu();
        }
        else if (srednicazewn>299&& srednicazewn<=329)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(17).getCenaWkladu();
        }
        else if (srednicazewn>329&& srednicazewn<=359)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(18).getCenaWkladu();
        }
        else if (srednicazewn>359&& srednicazewn<=389)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(19).getCenaWkladu();
        }
        else if (srednicazewn>389&& srednicazewn<=410)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(20).getCenaWkladu();
        }
        else if (srednicazewn>410&& srednicazewn<=500)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(21).getCenaWkladu();
        }
        else if (srednicazewn>500&& srednicazewn<=550)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(21).getCenaWkladu();
        }
        else if (srednicazewn>550)
        {
            cenaWkladu=WkladyMetaloweRepo.getById(21).getCenaWkladu() * 2;
        }

    return cenaWkladu ;}

}
