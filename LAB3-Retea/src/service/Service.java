package service;

import domain.Entity;
import domain.Prietenie;
import domain.Utilizator;
import domain.validators.ValidationException;

public interface Service<ID> {
    /**
     * @param utilizator - entitatea care trebuie adaugata
     * @return adevarat daca entitatea este adaugata
     *         altfel fals
     * @throws IllegalArgumentException
     *                  daca entitatea este egala cu null
     * @throws ValidationException
     *                  daca validarea nu a putut fi efectuata.
     */
    boolean addUtilizator(Utilizator utilizator);


    /**
     * @param email - parametrul dupa care realizam stergerea
     * @return s-a sters entitatea
     *         altefl null
     * @throws IllegalArgumentException
     *                  daca id-ul este null
     */
    Entity<ID> deleteUtilizator(String email);


    /**
     * @param email1 si
     * @param email2 - emailurile utilizatorilor intre care creeam prietenia
     *
     * @return adevarat daca a fost cvreeata
     *         fals atfel
     * @throws IllegalArgumentException
     *                  daca vreun email e null
     * @throws ValidationException
     *                  daca este acelasi email in ambele campuri
     */
    boolean createPrietenie(String email1, String email2);


    /**
     *  @param email1 si
     *  @param email2 - emailurile dintre care trebuie sa stergem prietenia
     *
     *  @return daca prietenia exista, se sterge
     *          null daca nu
     *  @throws IllegalArgumentException
     *               daca unul din cele 2 e null
     */
    Entity<ID> deletePrietenie(String email1, String email2);


    /**
    * @return un iterator cu toti utilizatorii
    */
    Iterable<Utilizator> getAllUtilizatori();


    /**
     * @return un iterator cu toate prieteniile
     */
    Iterable<Prietenie> getAllPrietenii();
}

