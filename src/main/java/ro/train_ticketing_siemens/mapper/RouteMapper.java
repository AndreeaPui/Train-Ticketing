package ro.train_ticketing_siemens.mapper;


import org.springframework.stereotype.Component;
import ro.train_ticketing_siemens.domain.Route;
import ro.train_ticketing_siemens.dto.route.RouteRequestDTO;
import ro.train_ticketing_siemens.dto.route.RouteResponseDTO;

@Component
public class RouteMapper {

    public Route toEntity(RouteRequestDTO dto) {
        return Route.builder()
                .name(dto.name())
                .build();
    }

    public RouteResponseDTO toDto(Route route) {
        return new RouteResponseDTO(
                route.getId(),
                route.getName()
        );
    }
}
