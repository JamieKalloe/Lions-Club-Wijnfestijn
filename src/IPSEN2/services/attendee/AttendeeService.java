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

    /**
     * Instantiates a new Attendee service.
     */
    public AttendeeService()
    {
        this.validator = new AttendeeValidator();
        this.repository = new AttendeeRepository();
    }

    /**
     * All array list.
     *
     * @return the array list
     */
    public ArrayList<Attendee> all()
    {
        return repository.all();
    }

    /**
     * Find attendee.
     *
     * @param id the id
     * @return the attendee
     */
    public Attendee find(int id)
    {
        Attendee attendee = repository.find(id);
        return attendee;
    }

    /**
     * Create int.
     *
     * @param data the data
     * @return the int
     */
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

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
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

    /**
     * Update.
     *
     * @param data the data
     */
    public void update(HashMap data) {
        repository.update(data);
    }

}
