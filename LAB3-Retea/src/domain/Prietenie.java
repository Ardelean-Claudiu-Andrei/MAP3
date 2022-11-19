package domain;

import java.util.Objects;
import java.util.UUID;

public class Prietenie extends Entity<UUID>{


    private Utilizator utilizator1;
    private Utilizator utilizator2;

    public Prietenie(Utilizator utilizator1, Utilizator utilizator2) {
        this.utilizator1 = utilizator1;
        this.utilizator2 = utilizator2;
        this.setId(UUID.randomUUID());
    }

    public Utilizator getUtilizator1() {
        return utilizator1;
    }

    public Utilizator getUtilizator2() {
        return utilizator2;
    }

    public void setUtilizator1(Utilizator utilizator1) {
        this.utilizator1 = utilizator1;
    }

    public void setUtilizator2(Utilizator utilizator2) {
        this.utilizator2 = utilizator2;
    }

    @Override
    public String toString() {
        return "\nPrietenie: \n" +
                "Utilizatorul = " + utilizator1.getFirstName() +
                " " +utilizator1.getLastName() +
                ", " + utilizator1.getEmail() +

                ",\nUtilizatorul = " + utilizator2.getFirstName() +
                " " +utilizator2.getLastName() +
                ", " + utilizator2.getEmail() +
                ';' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prietenie prietenie = (Prietenie) o;
        return Objects.equals(utilizator1, prietenie.utilizator1) && Objects.equals(utilizator2, prietenie.utilizator2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilizator1, utilizator2);
    }
}
