package service;

import domain.Entity;
import domain.Prietenie;
import domain.Utilizator;
import domain.validators.ValidationException;
import repository.Repository0;
import java.util.UUID;

public class Service0 implements Service<UUID> {
    private Repository0<UUID, Utilizator> utilizatorRepo;
    private Repository0<UUID, Prietenie> prietenieRepo;

    public Service0(Repository0<UUID, Utilizator> utilizatorRepo, Repository0<UUID, Prietenie> prietenieRepo) {
        this.utilizatorRepo = utilizatorRepo;
        this.prietenieRepo = prietenieRepo;
    }

    @Override
    public boolean addUtilizator(Utilizator utilizator) {

        Entity<UUID> u = null;
        try {
            if (utilizator.getFirstName() == null)
                throw new IllegalArgumentException("Emailul nu trebuie sa fie null!");
            else if (getUtilizatorByEmail(utilizator.getEmail()) != null)
                throw new IllegalArgumentException("Exista deja un cont cu acest email!");
            u = utilizatorRepo.save(utilizator);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        if (u != null) {
            throw new ValidationException("Exista un alt utilizator cu acest ID");
//            return false;
        }

        return true;
    }

    @Override
    public Entity<UUID> deleteUtilizator(String email) {
        Utilizator u = null;
        try {
            u = getUtilizatorByEmail(email);
            if (u == null) {
                System.err.println("Nu exista niciun utilizator cu acest ID!");
                return null;
            }

            for (Utilizator friend : u.getFriends()) {
                for (Prietenie p : prietenieRepo.findAll())
                    if ((p.getUtilizator1().equals(u) && p.getUtilizator2().equals(friend)) || (p.getUtilizator1().equals(friend) && p.getUtilizator2().equals(u))) {
                        prietenieRepo.delete(p.getId());
                        break;
                    }
                friend.removeFriend(u);
            }


            u = (Utilizator) utilizatorRepo.delete(u.getId());
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

        if (u == null) {
           throw new ValidationException("Nu exista utilizatorul!");
//            return null;
        }

        return u;
    }

    @Override
    public boolean createPrietenie(String email1, String email2) {

        Entity<UUID> f = null;
        Utilizator u1, u2;
        try {
            if (email1 == null || email2 == null)
                throw new IllegalArgumentException("Emailul trebuie sa nu fie null!");

            u1 = getUtilizatorByEmail(email1);
            u2 = getUtilizatorByEmail(email2);
            if (u1 == null || u2 == null || u1.equals(u2))
                throw new ValidationException("Nu exisat cei doi utilizatori!");

            f = prietenieRepo.save(new Prietenie(u1, u2));
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        if (f != null) {
            System.err.println("Utilizatorii acestia sunt deja prieteni!");
            return false;
        }

        u1.addFriend(u2);
        u2.addFriend(u1);
        return true;
    }

    @Override
    public Entity<UUID> deletePrietenie(String email1, String email2) {
        Entity<UUID> f = null;
        Utilizator u1, u2;
        try {
            if (email1 == null || email2 == null)
                throw new IllegalArgumentException("Emailurile trebuie sa fie diferite de null!");

            u1 = getUtilizatorByEmail(email1);
            u2 = getUtilizatorByEmail(email2);

            if (u1 == null || u2 == null || u1.equals(u2))
                throw new ValidationException("Nu exisat cei doi utilizatori!");

            Iterable<Prietenie> l = prietenieRepo.findAll();
            for (Prietenie elem : l)
                if (
                        (elem.getUtilizator1().getId().equals(u1.getId()) && elem.getUtilizator2().getId().equals(u2.getId()))
                                || (elem.getUtilizator1().getId().equals(u2.getId()) && elem.getUtilizator2().getId().equals(u1.getId()))
                ) {
                    f = prietenieRepo.delete(elem.getId());
                    break;
                }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

        if (f == null) {
            System.err.println("Cei doi utilizatori nu sunt prieteni!");
            return null;
        }

        u1.removeFriend(u2);
        u2.removeFriend(u1);
        return f;
    }

    /**
     * @return un iterator cu toti utilizatorii
     */
    @Override
    public Iterable<Utilizator> getAllUtilizatori() {
        return utilizatorRepo.findAll();
    }

    /**
     * @return un iterator cu toate prieteniile
     */
    @Override
    public Iterable<Prietenie> getAllPrietenii() {
        return prietenieRepo.findAll();
    }

    public Utilizator getUtilizatorByEmail(String email) {
        Iterable<Utilizator> it = utilizatorRepo.findAll();
        for (Utilizator u : it)
            if (u.getEmail().equals(email))
                return u;
        return null;
    }
}
