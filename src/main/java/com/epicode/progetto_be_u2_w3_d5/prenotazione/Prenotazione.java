package com.epicode.progetto_be_u2_w3_d5.prenotazione;

import com.epicode.progetto_be_u2_w3_d5.evento.Evento;
import com.epicode.progetto_be_u2_w3_d5.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prenotazioni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrenotazione;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private int postiPrenotati;
}
