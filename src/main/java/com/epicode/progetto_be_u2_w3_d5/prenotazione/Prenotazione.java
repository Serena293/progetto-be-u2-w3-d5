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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private int postiPrenotati;
}

