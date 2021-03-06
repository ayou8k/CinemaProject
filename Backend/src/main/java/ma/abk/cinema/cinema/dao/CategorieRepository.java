package ma.abk.cinema.cinema.dao;

import ma.abk.cinema.cinema.entities.Categorie;
import ma.abk.cinema.cinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestController
@CrossOrigin("*")
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
