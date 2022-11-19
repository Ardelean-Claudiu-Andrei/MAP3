package domain.validators;
import domain.Prietenie;

public class PrietenieValidator implements Validator<Prietenie>{
    /**
     * Prietenii nu pot avea acelasi ID.
     */
    @Override
    public void validate(Prietenie entity) throws ValidationException {
        if (entity.getUtilizator1().getId() == entity.getUtilizator2().getId())
            throw new ValidationException("Prietenii nu pot avea acelasi ID!");
    }
}
