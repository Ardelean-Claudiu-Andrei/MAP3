package domain;
import domain.Entity;
import java.util.*;

public class Utilizator extends Entity<UUID>{
    private String firstName;
    private String lastName;
    private String email;
    private Map<UUID, Utilizator> friends;

    public Utilizator(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        friends = new HashMap<>();
        this.setId(UUID.randomUUID()); // genereaza un id random
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Iterable<Utilizator> getFriends() {return friends.values();}

    @Override
    public String toString() {
        return "Utilizatorul: " +
                "\nPrenumele: " + firstName  +
                ",\nNumele: " + lastName  +
                ",\nEmail-ul: " + email +
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizator)) return false;
        Utilizator that = (Utilizator) o;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode() {return Objects.hash(getFirstName(), getLastName(), getEmail());}

    // adugarea unui prieten
    public void addFriend(Utilizator u) {friends.put(u.id, u);}

    // stergerea unui prieten
    public boolean removeFriend(Utilizator u) {
        return friends.remove(u.id) != null;
    }

}