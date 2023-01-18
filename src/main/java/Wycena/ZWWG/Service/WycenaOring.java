
package Wycena.ZWWG.Service;

import Wycena.ZWWG.ZwwgApplication;
import Wycena.ZWWG.model.Mieszanka;
import Wycena.ZWWG.model.MieszankaRepo;
import Wycena.ZWWG.model.ObrotNaPrasie;
import Wycena.ZWWG.model.ObrotNaPrasieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import static java.lang.Math.pow;

@Service
public class WycenaOring {


    private static double koeficjentwymiarow (double n) {
        n=Math.round(n);
        double k = 1.0011;
        if (n==1) return 1.00015;
        else return (koeficjentwymiarow(n-1)*k);
    }

    public static double WycenaOring( int obrotNaPrasie, double cena,double gestosc, double srednicawewn,double przekroj, double iloscsztuk ,int marza) {

        double obwod=(srednicawewn+przekroj)*0.1*Math.PI;
        double polepow= pow((0.05*przekroj),2)*Math.PI;
        double objetosc=obwod*polepow;
        double waga = objetosc*gestosc*1.2*0.001;
        double wartoscsur=waga*cena;

        double cenaOring;
        double wartoscOring;
        double maxiloscgniazd=1;
        double srednicaZewn = srednicawewn + przekroj + przekroj;

        double koeficjentIlosciowyA=((1/iloscsztuk)*10000000)*2.5+1;
        double sr=(srednicaZewn*1.5+100)*(srednicaZewn*1.5+100);
        double koeficjentSrednica=1/sr;
        double koeficjentIlosciowy =koeficjentIlosciowyA*koeficjentSrednica+1+100/(Math.pow(iloscsztuk,1.0/5)*srednicaZewn);

        double koeficjientwartoscisurowca= wartoscsur*1.5 ;

        maxiloscgniazd = ( (400 / (srednicaZewn + 30)) * (400 / (srednicaZewn + 30)))*0.7+0.6;


        wartoscOring = (obrotNaPrasie *koeficjentIlosciowy *koeficjentwymiarow(srednicaZewn)) / (maxiloscgniazd) / 60 + koeficjientwartoscisurowca;

        cenaOring = ((wartoscOring * marza / 100)  + wartoscOring);
        cenaOring*=100;
        cenaOring=Math.round(cenaOring);
        cenaOring/=100;

        System.out.println();
        System.out.println(srednicawewn + " x "+ przekroj);
        System.out.println("wart sur " + wartoscsur);
        System.out.println(" koef WS  " +koeficjientwartoscisurowca );
        System.out.println("MaxIlGn" + maxiloscgniazd);
        System.out.println("Wart Or" + wartoscOring);
        System.out.println("Koef il" + koeficjentIlosciowyA + " "+ koeficjentSrednica+ " "+  " "+ koeficjentIlosciowy);
        System.out.println("KOEF DUZ WYM" + koeficjentwymiarow(srednicaZewn));
        System.out.println("Cena Or" + cenaOring);


        return (cenaOring);
    }
}
