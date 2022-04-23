package pl.edu.pw.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Screening> screenings;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(Long id, String title, List<Screening> screenings) {
        this.id = id;
        this.title = title;
        this.screenings = screenings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }
}
