package com.epicode.progetto_be_u2_w3_d5;

import com.epicode.progetto_be_u2_w3_d5.auth.Role;
import com.epicode.progetto_be_u2_w3_d5.evento.Evento;
import com.epicode.progetto_be_u2_w3_d5.evento.EventoRepository;
import com.epicode.progetto_be_u2_w3_d5.prenotazione.Prenotazione;
import com.epicode.progetto_be_u2_w3_d5.prenotazione.PrenotazioneRepository;
import com.epicode.progetto_be_u2_w3_d5.user.User;
import com.epicode.progetto_be_u2_w3_d5.user.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProgettoBeU2W3D5Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoBeU2W3D5Application.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository, EventoRepository eventoRepository, PrenotazioneRepository prenotazioneRepository) {
		return args -> {
			Faker faker = new Faker();

			List<User> users = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setUsername(faker.name().username());
				user.setEmail(faker.internet().emailAddress());
				user.setPassword(faker.internet().password());
				user.setRole(i % 2 == 0 ? Role.ROLE_USER : Role.ROLE_ORGANIZZATORE);
				users.add(user);
			}
			userRepository.saveAll(users);

			List<Evento> eventi = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				User organizer = users.get(faker.number().numberBetween(0, users.size())); // Scegli un organizzatore casuale
				Evento evento = new Evento();
				evento.setTitolo(faker.book().title());
				evento.setDescrizione(faker.lorem().paragraph());
				evento.setData(LocalDate.now().plusDays(faker.number().numberBetween(1, 30))); // Imposta una data futura
				evento.setLocation(faker.address().city());
				evento.setNumeroPartecipanti(faker.number().numberBetween(10, 100)); // Correzione della parentesi
				evento.setOrganizzatore(organizer);
				eventi.add(evento);
			}
			eventoRepository.saveAll(eventi);

			List<Prenotazione> prenotazioni = new ArrayList<>();
			for (Evento evento : eventi) {
				for (User user : users) {
					if (faker.number().numberBetween(0, 10) < 7) { // Il 70% delle volte prenota un posto
						Prenotazione prenotazione = new Prenotazione();
						prenotazione.setUser(user);
						prenotazione.setEvento(evento);
						prenotazione.setPostiPrenotati(faker.number().numberBetween(1, 3));
						prenotazioni.add(prenotazione);
					}
				}
			}
			prenotazioneRepository.saveAll(prenotazioni);
		};
	}
}
