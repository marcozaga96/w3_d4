package marcozagaria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import marcozagaria.dao.AttendancesDAO;
import marcozagaria.dao.EventsDAO;
import marcozagaria.dao.LocationsDAO;
import marcozagaria.dao.PeopleDAO;
import marcozagaria.entities.*;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("w3_d4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EventsDAO eventsDAO = new EventsDAO(em);
        LocationsDAO locationsDAO = new LocationsDAO(em);
        PeopleDAO peopleDAO = new PeopleDAO(em);
        AttendancesDAO attendancesDAO = new AttendancesDAO(em);

        Location stadio = new Location("stadio san nicola", "bari");
//        locationsDAO.save(stadio);
        Location location1 = locationsDAO.findById(1);
        Event concerto = new Event("concerto", LocalDate.of(2024, 10, 17), "concerto di vasco", TipoEvento.PRIVATO, 10000, location1);
//        eventsDAO.save(concerto);
        Event evento1 = eventsDAO.findById(1);
        Person marco = new Person("marco", "zagaria", "marcozagaria@live.com", LocalDate.of(1996, 12, 10), "M");
//        peopleDAO.save(marco);
        Person person1 = peopleDAO.findById(1);
        Attendance attendace = new Attendance(person1, evento1);
        attendancesDAO.save(attendace);

    }
}
