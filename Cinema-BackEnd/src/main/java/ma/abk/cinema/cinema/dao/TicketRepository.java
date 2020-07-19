package ma.abk.cinema.cinema.dao;

import ma.abk.cinema.cinema.entities.Seance;
import ma.abk.cinema.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestController
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
