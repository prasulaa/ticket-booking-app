package pl.edu.pw.service;

import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Screening;
import pl.edu.pw.dto.ScreeningDTO;
import pl.edu.pw.repository.ScreeningRepository;
import pl.edu.pw.service.mapper.ScreeningMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public ScreeningDTO getScreening(Long id) {
        Optional<Screening> screeningOpt = screeningRepository.findById(id);

        if (screeningOpt.isPresent()) {
            Screening screening = screeningOpt.get();
            return ScreeningMapper.map(screening);
        } else {
            throw new EntityNotFoundException();
        }
    }

}
