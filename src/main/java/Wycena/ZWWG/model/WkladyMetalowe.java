package Wycena.ZWWG.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name=" WkladyMetalowe")
@Data
@NoArgsConstructor
public class WkladyMetalowe {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Integer id;
    private String typWymiar;
    private String tp;
    private double cenaWkladu;


    public WkladyMetalowe(Integer id, String typWymiar,String tp, double cenaWkladu) {
        this.id = id;
        this.typWymiar = typWymiar;
        this.tp = tp;
        this.cenaWkladu = cenaWkladu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypWymiar() {
        return typWymiar;
    }

    public void setTypWymiar(String typWymiar) {
        this.typWymiar = typWymiar;
    }

    public double getCenaWkladu() {
        return cenaWkladu;
    }

    public void setCenaWkladu(double cenaWkladu) {
        this.cenaWkladu = cenaWkladu;
    }
}
