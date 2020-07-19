package ma.abk.cinema.cinema.service;

import ma.abk.cinema.cinema.ICinemaInitService;
import ma.abk.cinema.cinema.dao.*;
import ma.abk.cinema.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository VilleRepo=null;
    @Autowired
    private CinemaRepository CinemaRepo=null;
    @Autowired
    private SalleRepository SalleRepo;
    @Autowired
    private PlaceRepository PlaceRepo;
    @Autowired
    private SeanceRepository SeanceRepo;
    @Autowired
    private FilmRepository FilmRepo;
    @Autowired
    private ProjectionFilmRepository ProjectionFilmRepo;
    @Autowired
    private CategorieRepository CategorieRepo;
    @Autowired
    private TicketRepository TicketRepo;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat simpleHourFormat=new SimpleDateFormat("HH-mm");
    int codepayement=0;


    @Override
    public void initVilles() {
    Stream.of("Berrechid","Casablanca","Houara","Taroudant").forEach(nomville->{
        Ville ville=new Ville();
        ville.setName(nomville);
        VilleRepo.save(ville);
    });
    }

    @Override
    public void initCinemas() {
        VilleRepo.findAll().forEach(ville -> {
        Stream.of("Cinema1","Cinema2","Cinema3").forEach(nomCinema->{
        Cinema cinema=new Cinema();
        cinema.setName(nomCinema);
        cinema.setNbsalles(3+(int)(Math.random()*10));
        cinema.setAltitude(3+(int)(Math.random()*10)+Math.random()*10);
        cinema.setLatitude(3+(int)(Math.random()*10)+Math.random()*10);
        cinema.setLongtitude(3+(int)(Math.random()*10)+Math.random()*10);
        cinema.setVille(ville);
        CinemaRepo.save(cinema);
    });
});
    }

    @Override
    public void initSalles() {
    CinemaRepo.findAll().forEach(cinema -> {
        Stream.of("Salle1","Salle2","Salle3","Salle4").forEach(salle_name->{
            Salle s=new Salle();
            s.setCinema(cinema);
            s.setName(salle_name);
            s.setNbplace(5+(int)(Math.random()*10));
            SalleRepo.save(s);
        });

    });
    }

    @Override
    public void initPlaces() {
        SalleRepo.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNbplace(); i++)
            {
                Place p = new Place();
                p.setNum(i);
                p.setAltitude(1 + (int) (Math.random() * 10) + Math.random() * 10);
                p.setLatitude(1 + (int) (Math.random() * 10) + Math.random() * 10);
                p.setLongtitude(1 + (int) (Math.random() * 10) + Math.random() * 10);
                p.setSalle(salle);

                PlaceRepo.save(p);

            }

        });
    }

    @Override
    public void initSeances() {
        int heure=10;
        for (int i = 0; i < 4; i++) {
            Seance seance=new Seance();
            String hours=heure+"-00";
            try {
                seance.setHeureDebut(simpleHourFormat.parse(hours));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SeanceRepo.save(seance);
            heure+=2;
        }

    }

    @Override
    public void initCategories() {
        Stream.of("Action ","Drama","Comedy").forEach(categorie->{
            Categorie cat=new Categorie();
            cat.setName(categorie);
           CategorieRepo.save(cat);
        });

    }

    @Override
    public void initFilms() {
    Stream.of("Parasite","Purge","uncut gems","Catch Me If You Can").forEach(film_name->{
        Film film=new Film();
        film.setTitre(film_name);
        try {
            film.setDateSortie(simpleDateFormat.parse("23/11/2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        film.setCategorie(CategorieRepo.getOne(1+((long) (Math.random() * (CategorieRepo.findAll().size())))));
        film.setDescription("Description film");
        film.setDuree(2);
        film.setPhoto(film_name);
        film.setRealisateur("Realisateur");
        FilmRepo.save(film);
    });
    }

    @Override
    public void initProjections() {
        CinemaRepo.findAll().forEach(cinema -> {
            cinema.getSalles().forEach( salle -> {
                List<Film> films = FilmRepo.findAll();
                SeanceRepo.findAll().forEach(seance -> {
                    ProjectionFilm p=new ProjectionFilm();
                    p.setFilm(FilmRepo.getOne(1+((long) (Math.random() * (FilmRepo.findAll().size())))));
                    p.setPrix((int)(Math.random()*10)+50);
                    p.setSeance(seance);
                    p.setSalle(salle);
                    try {
                        p.setDateProjection(simpleDateFormat.parse("23/11/2020"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ProjectionFilmRepo.save(p);
                });


            });

        });
    }

    @Override
    public void initTickets() {
//        ProjectionFilmRepo.findAll().forEach(projection ->{
//                    projection.getSalle().getPlaces().forEach(place -> {
//                                Ticket ticket = new Ticket();
//                                ticket.setPlace(place);
//                                ticket.setCodePayement(1+(int)(Math.random()*10));
//                                ticket.setNomclient("Client"+(1+(int)(Math.random()*10)));
//                    ticket.setPrix(projection.getPrix());
//                                ticket.setProjectionFilm(projection);
//                                ticket.setReserve(false);
//                                TicketRepo.save(ticket);
//                            }
//                    );
//                });
        ProjectionFilmRepo.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setCodePayement(codepayement++);
                ticket.setPlace(place);
                ticket.setProjectionFilm(projection);
            ticket.setPrix(5);
                ticket.setReserve(false);
                TicketRepo.save(ticket);
            });
        });


    }
}
