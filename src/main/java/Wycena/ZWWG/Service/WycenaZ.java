package Wycena.ZWWG.Service;
import org.springframework.stereotype.Service;

import static java.lang.Math.pow;

@Service
public class WycenaZ {

    private static double koeficjentwymiarow (double n) {
        n=Math.round(n);
        double k = 1.0011;
        if (n==1) return 1.00015;
        else return (koeficjentwymiarow(n-1)*k);
    }

    public static double WycenaZ( int obrotNaPrasie,  double cena,double gestosc, double srednicawewn,double srednicazewn,  double wysokosc ,double iloscsztuk,int marza) {

        double r=Math.pow((srednicawewn*0.1)/2,2);
        double rr =Math.pow(srednicazewn*0.1/2,2);
        double objetosc= (rr-r)*Math.PI*wysokosc*0.1;

        double wartoscsur=(objetosc*gestosc*1.2*0.001*cena);
        double cenaZ;
        double wartoscZ;
        double maxiloscgniazd=1;

        double koeficjentIlosciowyA=((1/iloscsztuk)*10000000)*2.1+1;
        double sr=(srednicazewn*1.5+100)*(srednicazewn*1.5+100);
        double koeficjentSrednica=1/sr;
        double koeficjentIlosciowy =koeficjentIlosciowyA*koeficjentSrednica+1+100/(Math.pow(iloscsztuk,1.0/5)*srednicazewn);



        maxiloscgniazd = ( (360 / (srednicazewn + 10)) * (360 / (srednicazewn + 10)))*0.65+0.6;


        wartoscZ = (obrotNaPrasie *koeficjentIlosciowy *koeficjentwymiarow(srednicazewn)) / (maxiloscgniazd) / 60 + wartoscsur;

        cenaZ = ((wartoscZ * marza / 100)  + wartoscZ);
        cenaZ *=100;
        cenaZ =Math.round(cenaZ );
        cenaZ/=100;

        System.out.println();
        System.out.println(srednicawewn + " x "+ srednicazewn+ "x"+ wysokosc);
        System.out.println("wart sur " + wartoscsur);
        System.out.println(" koef WS  "  );

        System.out.println("MaxIlGn" + maxiloscgniazd);
        System.out.println("Wart zgarniacza" + wartoscZ);
        System.out.println("Koef il  a " + koeficjentIlosciowyA + " Koef srednnica "+ " "+ koeficjentSrednica+ " "+ " Koef iloscioy"+ " "+ koeficjentIlosciowy);
        System.out.println("KOEF DUZ WYM" + koeficjentwymiarow(srednicazewn));
        System.out.println("Cena zgarniacza" + cenaZ );


        return (cenaZ );
    }
}