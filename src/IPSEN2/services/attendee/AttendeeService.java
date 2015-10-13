package IPSEN2.services.attendee;

import IPSEN2.models.attendee.Attendee;
import IPSEN2.repositories.attendee.AttendeeRepository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philip on 13-10-15.
 */
public class AttendeeService {
    private AttendeeRepository repository;

    public AttendeeService() {
        this.repository = new AttendeeRepository();
    }

    public ArrayList<Attendee> all() { return new ArrayList<Attendee>(); }

    public Attendee find(int id) {
        Attendee attendee = repository.find(id);
        return attendee;
    }

    public int create(HashMap data) {
       return repository.create(data);
    }

    public Object delete(int id) {
        repository.delete(id);
        return true;
    }

}
