package com.epicode.progetto_be_u2_w3_d5.evento;

import com.epicode.progetto_be_u2_w3_d5.auth.Role;
import com.epicode.progetto_be_u2_w3_d5.user.User;
import com.epicode.progetto_be_u2_w3_d5.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventoService {


    private final EventoRepository eventoRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, UserRepository userRepository) {
        this.eventoRepository = eventoRepository;
        this.userRepository = userRepository;
    }

    public Evento creaEvento(String titolo, String descrizione, LocalDate data, String luogo, int numeroPartecipanti, Long userId) {
        User organizzatore = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // Verifica se l'utente ha il ruolo di organizzatore
        if ((!organizzatore.getRoles().contains(Role.ROLE_ORGANIZZATORE))) {
            throw new RuntimeException("L'utente non ha i permessi necessari per creare eventi");
        }

        Evento evento = new Evento();
        evento.setTitolo(titolo);
        evento.setDescrizione(descrizione);
        evento.setData(data);
        evento.setLocation(luogo);
        evento.setNumeroPartecipanti(numeroPartecipanti);
        evento.setOrganizzatore(organizzatore);

        return eventoRepository.save(evento);
    }
}

