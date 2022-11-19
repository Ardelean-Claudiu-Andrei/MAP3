import domain.Entity;
import domain.Utilizator;
import domain.Prietenie;
import domain.validators.PrietenieValidator;
import domain.validators.UtilizatorValidator;
import domain.validators.ValidationException;
import repository.Repository0;
import repository.memory.InMemoryRepository0;
import service.Service;
import service.Service0;
import ui.Consola;
import ui.UI;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        InMemoryRepository0<UUID, Utilizator> utilizatorRepo = new InMemoryRepository0<>(new UtilizatorValidator());
        InMemoryRepository0<UUID, Prietenie> prietenieeRepo = new InMemoryRepository0<>(new PrietenieValidator());

        Service0 service = new Service0(utilizatorRepo,prietenieeRepo);
        Consola consola  = new Consola(service);

        consola.runUI();
    }
}