package ma.abk.cinema.cinema.entities;
import org.springframework.data.rest.core.config.Projection;

public class ProjectionTicket {


    @Projection(name = "ticket", types=Ticket.class)
    public interface TicketProjection {
        public Long getId();
        public String getnomclient();
        public double getPrix();
        public Integer getCodePayement();
        public boolean getReserve();
        public Place getPlace();
    }
}
