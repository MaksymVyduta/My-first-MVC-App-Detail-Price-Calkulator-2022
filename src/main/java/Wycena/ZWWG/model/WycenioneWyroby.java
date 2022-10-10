package Wycena.ZWWG.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name="WycenioneWyroby" )
@Data
@NoArgsConstructor
public class WycenioneWyroby {
    @Id

    private Integer id;
    private String typWyrobu;
    @ManyToOne(cascade = CascadeType.ALL)
    private Mieszanka material;
    private double iloscSztuk;
    @ManyToOne(cascade = CascadeType.ALL)
    @Nullable
    private WkladyMetalowe wklad;
    private double cenaWyrobu;
    private String wymiar;

    public WycenioneWyroby( int id ,String typWyrobu, Mieszanka material, double iloscSztuk, double cenaWyrobu, String wymiar) {
        this.id = id;
        this.typWyrobu = typWyrobu;
        this.material = material;
        this.iloscSztuk = iloscSztuk;
        this.cenaWyrobu = cenaWyrobu;
        this.wymiar=wymiar;
    }
    public WycenioneWyroby( String typWyrobu, Mieszanka material,WkladyMetalowe wklad, double iloscSztuk, double cenaWyrobu, String wymiar) {
        this.id = id;
        this.typWyrobu = typWyrobu;
        this.material = material;
        this.wklad=wklad;
        this.iloscSztuk = iloscSztuk;
        this.cenaWyrobu = cenaWyrobu;
        this.wymiar=wymiar;
    }

    public WycenioneWyroby(Integer id, String typWyrobu, Mieszanka material, double iloscSztuk, @Nullable WkladyMetalowe wklad, double cenaWyrobu,  String wymiar) {
        this.id = id;
        this.typWyrobu = typWyrobu;
        this.material = material;
        this.iloscSztuk = iloscSztuk;
        this.wklad = wklad;
        this.cenaWyrobu = cenaWyrobu;
        this.wymiar=wymiar;
    }
}

