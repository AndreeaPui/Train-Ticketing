package ro.train_ticketing_siemens.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.train_ticketing_siemens.domain.Booking;
import ro.train_ticketing_siemens.domain.Schedule;
import ro.train_ticketing_siemens.domain.Train;
import ro.train_ticketing_siemens.dto.request.DelayRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleResponseDTO;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import ro.train_ticketing_siemens.errorhandling.exceptions.ResourceNotFoundException;
import ro.train_ticketing_siemens.mapper.ScheduleMapper;
import ro.train_ticketing_siemens.repository.BookingRepository;
import ro.train_ticketing_siemens.repository.ScheduleRepository;
import ro.train_ticketing_siemens.repository.TrainRepository;
import ro.train_ticketing_siemens.service.EmailService;
import ro.train_ticketing_siemens.service.ScheduleService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TrainRepository trainRepository;
    private final ScheduleMapper scheduleMapper;
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    @Override
    public ScheduleResponseDTO create(ScheduleRequestDTO dto) {
        Train train = trainRepository.findById(dto.trainId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        Schedule schedule = scheduleMapper.toEntity(dto, train);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public List<ScheduleResponseDTO> getAll() {
        return scheduleRepository.findByDeletedFalse()
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    @Override
    public ScheduleResponseDTO getById(UUID id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        return scheduleMapper.toDto(schedule);
    }

    @Override
    public List<ScheduleResponseDTO> getByTrainId(UUID trainId) {
        return scheduleRepository.findByTrainIdAndDeletedFalse(trainId)
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        Schedule schedule = scheduleRepository.findById(id)
                .filter(s -> !s.getDeleted())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setDeleted(true);

        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleResponseDTO update(UUID id, ScheduleRequestDTO dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .filter(s -> !s.getDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode._1004_SCHEDULE_NOT_FOUND,
                        id
                ));

        Train train = trainRepository.findById(dto.trainId())
                .filter(t -> !t.getDeleted())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        schedule.setTrain(train);
        schedule.setDepartureTime(dto.departureTime());
        schedule.setDelayMinutes(dto.delayMinutes());

        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    @Override
    @Transactional
    public ScheduleResponseDTO reportDelay(UUID scheduleId, DelayRequestDTO dto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .filter(s -> !s.getDeleted())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setDelayMinutes(dto.delayMinutes());

        Schedule savedSchedule = scheduleRepository.save(schedule);

        List<Booking> bookings = bookingRepository.findByScheduleIdAndDeletedFalse(scheduleId);

        for (Booking booking : bookings) {
            emailService.sendDelayNotification(booking, dto.delayMinutes());
        }

        return scheduleMapper.toDto(savedSchedule);
    }
}
