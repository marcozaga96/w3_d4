package marcozagaria.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import marcozagaria.entities.Concerto;
import marcozagaria.entities.Event;
import marcozagaria.entities.Genere;
import marcozagaria.entities.PArtitaDiCalcio;

import java.util.List;

public class EventsDAO {
    private EntityManager em;

    public EventsDAO(EntityManager em) {
        this.em = em;
    }


    public Event save(Event evento) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(evento);
            t.commit();
            System.out.println("Evento - " + evento.getTitolo() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return evento;
    }

    public Event findById(long id) {
        return em.find(Event.class, id);
    }

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            Event found = em.find(Event.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Evento eliminato");
            } else System.out.println("Evento non trovato");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Concerto> getConcertiInStreaming(boolean inStreaming) {
        return em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :inStreaming", Concerto.class)
                .setParameter("inStreaming", inStreaming)
                .getResultList();
    }

    public List<Concerto> getConcertiPerGenere(Genere genere) {
        return em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere", Concerto.class)
                .setParameter("genere", genere)
                .getResultList();
    }

    public List<PArtitaDiCalcio> getPartiteVinteInCasa() {
        return em.createQuery("SELECT p FROM PArtitaDiCalcio p WHERE p.squadraVincente = p.squadraDiCasa", PArtitaDiCalcio.class)
                .getResultList();
    }

    public List<PArtitaDiCalcio> getPartiteVinteInTrasferta() {
        return em.createQuery("SELECT p FROM PArtitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite", PArtitaDiCalcio.class)
                .getResultList();
    }
}
