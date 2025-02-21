package com.epicode.progetto_be_u2_w3_d5.evento;

import com.epicode.progetto_be_u2_w3_d5.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEvento;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false, length = 1000)
    private String descrizione;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int numeroPartecipanti;

    @ManyToOne
    @JoinColumn(name = "id_organizzatore", nullable = false)
    private User organizzatore;
}
