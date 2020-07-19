package ma.abk.cinema.cinema;

import ma.abk.cinema.cinema.entities.Film;
import ma.abk.cinema.cinema.entities.Salle;
import ma.abk.cinema.cinema.service.CinemaInitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
    @Autowired
    RepositoryRestConfiguration restConfiguration;
    @Autowired
    CinemaInitServiceImpl cinemaInitService;
    //to use cinemaInitService and keep attributes auto wired it must not INSTANTIATE classes manually it must be autowired to do so it must be a service
    //https://www.moreofless.co.uk/spring-mvc-java-autowired-component-null-repository-service/
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
        //implement commandlinerunner to use run method for additionnal code
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Film.class);
        restConfiguration.exposeIdsFor(Salle.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initSeances();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();
    }
}
