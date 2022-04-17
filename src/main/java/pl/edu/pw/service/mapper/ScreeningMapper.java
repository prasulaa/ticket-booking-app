package pl.edu.pw.service.mapper;

import pl.edu.pw.domain.Screening;
import pl.edu.pw.dto.ScreeningDTO;

public class ScreeningMapper {

    public static ScreeningDTO map(Screening screening) {
        if(screening == null) {
            return null;
        } else {
            return mapScreening(screening);
        }
    }

    private static ScreeningDTO mapScreening(Screening screening) {
        ScreeningDTO mappedScreening = new ScreeningDTO();
        mappedScreening.setId(screening.getId());
        mappedScreening.setDate(screening.getDate());
        mappedScreening.setTime(screening.getTime());
        mappedScreening.setMovie(MovieMapper.map(screening.getMovie()));
        mappedScreening.setRoom(RoomMapper.map(screening.getRoom()));
        mappedScreening.setAvailableSeats(SeatsMapper.mapAvailableSeats(screening.getRoom(), screening.getReservations()));

        return mappedScreening;
    }

}
