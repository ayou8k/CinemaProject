package ma.abk.cinema.cinema.service;

import ma.abk.cinema.cinema.dao.VilleRepository;
import ma.abk.cinema.cinema.dao.VilleRepository;
import ma.abk.cinema.cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//this controller is "sur mesure" to do things that arent' generic which are done by @@RepositoryRestController in the
//spring-boot-starter-data-rest dependancy that get added to the repository in question
@RestController
public class VilleRestController {
    @Autowired
    private VilleRepository villeRepository;
    @GetMapping("/cities")
    public List<Ville> villes(){
        return villeRepository.findAll();
    }
    @GetMapping("/city/{id}")
    public Ville ville(@PathVariable Long id){
        return villeRepository.getOne(id);
    }
}
