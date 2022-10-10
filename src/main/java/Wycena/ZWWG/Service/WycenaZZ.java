package Wycena.ZWWG.Service;

public class WycenaZZ {

    private static double koeficjentwymiarow (double n) {
        n=Math.round(n);
        double k = 1.0011;
        if (n==1) return 1.00015;
        else return (koeficjentwymiarow(n-1)*k);
    }

    public static double WycenaZZ(String nazwa, double cenaWkladu, int obrotNaPrasie,  double cena,double gestosc, double srednicawewn,double srednicazewn,  double wysokosc ,double iloscsztuk,int marza) {

        double r=Math.pow((srednicawewn*0.1)/2,2);
        double rr =Math.pow(srednicazewn*0.1/2,2);
        double objetosc= (rr-r)*Math.PI*wysokosc*0.1;

        double wartsocgum=(objetosc*gestosc*1.2*0.001*cena);
        // if (nazwa.equals("FPM") || nazwa.equals("FKM") )
        // {wartsocgum=wartsocgum;}
        //wartsocgum=wartsocgum/2;}
        double wartoscsur=wartsocgum+cenaWkladu;

        double cenaZZ;
        double wartoscZZ;
        double maxiloscgniazd=1;

        double koeficjentIlosciowyA=((1/iloscsztuk)*10000000)*2.1+1;
        double sr=(srednicazewn*1.5+100)*(srednicazewn*1.5+100);
        double koeficjentSrednica=1/sr;
        double koeficjentIlosciowy =koeficjentIlosciowyA*koeficjentSrednica+1+100/(Math.pow(iloscsztuk,1.0/5)*srednicazewn);



        maxiloscgniazd = ( (360 / (srednicazewn + 10)) * (360 / (srednicazewn + 10)))*0.7+0.6;


        wartoscZZ = (obrotNaPrasie *koeficjentIlosciowy *koeficjentwymiarow(srednicazewn)) / (maxiloscgniazd) / 60 + wartoscsur;

        cenaZZ = ((wartoscZZ * marza / 100)  + wartoscZZ);
        cenaZZ *=100;
        cenaZZ =Math.round(cenaZZ );
        cenaZZ /=100;

        System.out.println();
        System.out.println(srednicawewn + " x "+ srednicazewn+ "x"+ wysokosc);
        System.out.println("wart gum OP " + wartsocgum);
        System.out.println("Cena wkladu  " + cenaWkladu);
        System.out.println("wart sur " + wartoscsur);
        System.out.println(" koef WS  "  );

        System.out.println("MaxIlGn" + maxiloscgniazd);
        System.out.println("Wart ZZ" + wartoscZZ);
        System.out.println("Koef il  a " + koeficjentIlosciowyA + " Koef srednnica "+ " "+ koeficjentSrednica+ " "+ " Koef iloscioy"+ " "+ koeficjentIlosciowy);
        System.out.println("KOEF DUZ WYM" + koeficjentwymiarow(srednicazewn));
        System.out.println("Cena ZZ" + cenaZZ );


        return (cenaZZ );
    }
}
