package com.epicode.progetto_be_u2_w3_d5.evento;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByOrganizerId(Long organizerId);

    Optional<Evento> findByTitle(String title);

    List<Evento> findByDate(String date);

    List<Evento> findByAvailableSeatsGreaterThan(int postiDisponibili);
}
