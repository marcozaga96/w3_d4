package marcozagaria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "gare_di_atletica")
public class GaraDiAtletica extends Event {
    @OneToMany
    private Set<Person> atleti;
    @OneToOne
    private Person vincitore;

    public GaraDiAtletica(Set<Person> atleti, Person vincitore) {
        this.atleti = atleti;
        this.vincitore = vincitore;
    }

    public GaraDiAtletica() {
    }

    public Set<Person> getAtleti() {
        return atleti;
    }

    public void setAtleti(Set<Person> atleti) {
        this.atleti = atleti;
    }

    public Person getVincitore() {
        return vincitore;
    }

    public void setVincitore(Person vincitore) {
        this.vincitore = vincitore;
    }
}
