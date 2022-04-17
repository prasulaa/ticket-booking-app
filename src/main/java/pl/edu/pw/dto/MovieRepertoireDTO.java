package pl.edu.pw.dto;

import java.util.List;

public class MovieRepertoireDTO {

    private Long id;
    private String title;
    private List<ScreeningTimeDTO> screeningTimes;

    public MovieRepertoireDTO() {
    }

    public MovieRepertoireDTO(Long id, String title, List<ScreeningTimeDTO> screeningTimes) {
        this.id = id;
        this.title = title;
        this.screeningTimes = screeningTimes;
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

    public List<ScreeningTimeDTO> getScreeningTimes() {
        return screeningTimes;
    }

    public void setScreeningTimes(List<ScreeningTimeDTO> screeningTimes) {
        this.screeningTimes = screeningTimes;
    }
}
