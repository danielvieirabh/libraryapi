package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor , UUID> { //Classe e tipo de ID

}
