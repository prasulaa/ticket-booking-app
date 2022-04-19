package pl.edu.pw.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Movie;
import pl.edu.pw.domain.Room;
import pl.edu.pw.domain.Screening;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    public DbInit(RoomRepository roomRepository, MovieRepository movieRepository, ScreeningRepository screeningRepository) {
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    @Override
    public void run(String... args) {
        Room room1 = new Room(10, 10);
        Room room2 = new Room(15, 10);
        Room room3 = new Room(8, 7);

        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);

        Movie movie1 = new Movie("Movie 1");
        List<Screening> screenings1 = new ArrayList<>();
        screenings1.add(new Screening(
                LocalDate.parse("2022-04-20"),
                LocalTime.parse("15:30:00"),
                movie1,
                room1));
        screenings1.add(new Screening(
                LocalDate.parse("2022-04-21"),
                LocalTime.parse("17:00:00"),
                movie1,
                room3));
        movie1.setScreenings(screenings1);

        Movie movie2 = new Movie("Movie 2");
        List<Screening> screenings2 = new ArrayList<>();
        screenings2.add(new Screening(
                LocalDate.parse("2022-04-20"),
                LocalTime.parse("12:00:00"),
                movie2,
                room2));
        screenings2.add(new Screening(
                LocalDate.parse("2022-04-22"),
                LocalTime.parse("20:00:00"),
                movie2,
                room3));
        movie2.setScreenings(screenings2);

        Movie movie3 = new Movie("Movie 3");
        List<Screening> screenings3 = new ArrayList<>();
        screenings3.add(new Screening(
                LocalDate.parse("2022-04-19"),
                LocalTime.parse("14:00:00"),
                movie3,
                room2));
        screenings3.add(new Screening(
                LocalDate.parse("2022-04-20"),
                LocalTime.parse("16:15:00"),
                movie3,
                room1));
        movie3.setScreenings(screenings3);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
    }

}
