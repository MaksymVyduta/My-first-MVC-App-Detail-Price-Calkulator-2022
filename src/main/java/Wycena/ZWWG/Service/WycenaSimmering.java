package Wycena.ZWWG.Service;
import org.springframework.stereotype.Service;

import static java.lang.Math.pow;

@Service
public class WycenaSimmering {

    private static double koeficjentwymiarow (double n) {
        n=Math.round(n);
        double k = 1.0011;
        if (n==1) return 1.00015;
        else return (koeficjentwymiarow(n-1)*k);
    }

    public static double WycenaSimmering(String nazwa, double cenaWkladu, int obrotNaPrasie,  double cena,double gestosc, double srednicawewn,double srednicazewn,  double wysokosc ,double iloscsztuk,int marza) {

        double r=Math.pow((srednicawewn*0.1)/2,2);
        double rr =Math.pow(srednicazewn*0.1/2,2);
        double objetosc= (rr-r)*Math.PI*wysokosc*0.1;

        double wartsocgum=(objetosc*gestosc*1.2*0.001*cena);
       // if (nazwa.equals("FPM") || nazwa.equals("FKM") )
       // {wartsocgum=wartsocgum;}
        //wartsocgum=wartsocgum/2;}
        double wartoscsur=(wartsocgum/1.5)+cenaWkladu;

        double cenaSimmer;
        double wartoscSimmer;
        double maxiloscgniazd=1;

        double koeficjentIlosciowyA=((1/iloscsztuk)*10000000)*2.1+1;
        double sr=(srednicazewn*1.5+100)*(srednicazewn*1.5+100);
        double koeficjentSrednica=1/sr;
        double koeficjentIlosciowy =koeficjentIlosciowyA*koeficjentSrednica+1+100/(Math.pow(iloscsztuk,1.0/5)*srednicazewn);



        maxiloscgniazd = ( (400 / (srednicazewn + 30)) * (400 / (srednicazewn + 30)))*0.7+0.6;


        wartoscSimmer = (obrotNaPrasie *koeficjentIlosciowy *koeficjentwymiarow(srednicazewn)) / (maxiloscgniazd) / 60 + wartoscsur;

        cenaSimmer = ((wartoscSimmer * marza / 100)  + wartoscSimmer);
        cenaSimmer *=100;
        cenaSimmer =Math.round(cenaSimmer );
        cenaSimmer /=100;

        System.out.println();
        System.out.println(srednicawewn + " x "+ srednicazewn+ "x"+ wysokosc);
        System.out.println("wart gum OP " + wartsocgum);
        System.out.println("Cena wkladu  " + cenaWkladu);
        System.out.println("wart sur " + wartoscsur);
        System.out.println(" koef WS  "  );

        System.out.println("MaxIlGn" + maxiloscgniazd);
        System.out.println("Wart Simm" + wartoscSimmer);
        System.out.println("Koef il  a " + koeficjentIlosciowyA + " Koef srednnica "+ " "+ koeficjentSrednica+ " "+ " Koef iloscioy"+ " "+ koeficjentIlosciowy);
        System.out.println("KOEF DUZ WYM" + koeficjentwymiarow(srednicazewn));
        System.out.println("Cena Simmer" + cenaSimmer );


        return (cenaSimmer );
    }
}
