package ma.abk.cinema.cinema.entities;


import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name="p1",types={ProjectionFilm.class})
public interface ProjectionX {
    public Date getDateProjection();
    public double getPrix();
    public Salle getSalle();
    public Film getFilm();
    public Ticket getTickets();
    public Seance getSeance();
}
