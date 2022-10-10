package Wycena.ZWWG.model;

import javax.persistence.*;

@Entity
@Table(name="ObrotNaPrasie" )
public class ObrotNaPrasie {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Integer id;
    private Integer obrotNaPrasie;

    public Integer getObrotNaPrasie() {
        return obrotNaPrasie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObrotNaPrasie(Integer obrotNaPrasie) {

        this.obrotNaPrasie = obrotNaPrasie;
    }

    public ObrotNaPrasie(Integer id, Integer obrotNaPrasie) {
        this.id = id;
        this.obrotNaPrasie = obrotNaPrasie;
    }

    @Override
    public String toString() {
        return "ObrotNaPrasie{" +
                "id=" + id +
                ", obrotNaPrasie=" + obrotNaPrasie +
                '}';
    }

    public ObrotNaPrasie(Integer obrotNaPrasie) {
        this.obrotNaPrasie = obrotNaPrasie;
    }

    public ObrotNaPrasie() {
    }
}
