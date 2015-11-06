package IPSEN2.services.attendee;

import IPSEN2.models.attendee.Attendee;
import IPSEN2.repositories.attendee.AttendeeRepository;
import IPSEN2.services.message.Messaging;
import IPSEN2.validators.attendee.AttendeeValidator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philip on 13-10-15.
 */
public class AttendeeService {

    private AttendeeRepository repository;
    private AttendeeValidator validator;

    public AttendeeService()
    {
        this.validator = new AttendeeValidator();
        this.repository = new AttendeeRepository();
    }

    public ArrayList<Attendee> all()
    {
        return repository.all();
    }

    public Attendee find(int id)
    {
        Attendee attendee = repository.find(id);
        return attendee;
    }

    public int create(HashMap data)
    {
        boolean isValid = this.validator.validate(data);

        if (!isValid)
        {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Aanwezigen invoerfout",
                    "Een van de velden om een aanwezigen toe te voegen is niet aanwezig."
            );

            return -1;
        }

        int exists = repository.exists(data);

        if (exists == 0)
        {
            return repository.create(data);
        }

        return exists;
    }

    public boolean delete(int id)
    {
        Attendee attendee = repository.find(id);

        if (attendee != null)
        {
            repository.delete(id);

            return true;
        }

        return false;
    }

    public void update(HashMap data) {
        repository.update(data);
    }

}
