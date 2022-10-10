package Wycena.ZWWG.model;

import javax.persistence.*;


@Entity
@Table(name="Mieszanka" )
public class Mieszanka {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Integer id;
    private String nazwa;
    private String kolor;
    private float gestosc;
    private float cena;

    public Mieszanka() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mieszanka{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", kolor='" + kolor + '\'' +
                ", gestosc=" + gestosc +
                ", cena=" + cena +
                '}';
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setGestosc(float gestosc) {
        this.gestosc = gestosc;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Integer getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public float getGestosc() {
        return gestosc;
    }

    public float getCena() {
        return cena;
    }

    public Mieszanka(String nazwa, float gestosc, float cena) {
        this.nazwa = nazwa;
        this.gestosc = gestosc;
        this.cena = cena;
    }

    public Mieszanka(Integer id, String nazwa, float gestosc, float cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.gestosc = gestosc;
        this.cena = cena;
    }

}
