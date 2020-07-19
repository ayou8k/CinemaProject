package ma.abk.cinema.cinema.dao;

import ma.abk.cinema.cinema.entities.ProjectionFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestController
public interface ProjectionFilmRepository extends JpaRepository<ProjectionFilm,Long> {
}
