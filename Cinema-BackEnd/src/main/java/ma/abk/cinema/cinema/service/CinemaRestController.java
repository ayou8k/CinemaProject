package ma.abk.cinema.cinema.service;

import ma.abk.cinema.cinema.dao.CinemaRepository;
import ma.abk.cinema.cinema.dao.FilmRepository;
import ma.abk.cinema.cinema.dao.TicketRepository;
import ma.abk.cinema.cinema.dao.VilleRepository;
import ma.abk.cinema.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
public class CinemaRestController {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping("/cines")
    public List<Cinema> cinemas(){
        return cinemaRepository.findAll();
    }
    @GetMapping("/cine/{id}")
    public Cinema cine(@PathVariable Long id){
        return cinemaRepository.getOne(id);
    }
    @CrossOrigin("*")
    @GetMapping(path = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name="id")Long id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
        System.err.println(System.getProperty("user.home")+"/cinema/images/"+photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }
    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketFrom ticketFrom){
        List<Ticket> ticketList = new ArrayList<>();
        ticketFrom.getTickets().forEach(id->{
            Ticket ticket = ticketRepository.findById(id).get();
            ticket.setNomclient(ticketFrom.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketFrom.getCodePayement());
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }
}

