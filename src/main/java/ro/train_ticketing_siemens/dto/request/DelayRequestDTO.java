package ro.train_ticketing_siemens.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DelayRequestDTO(

        @NotNull
        @Min(1)
        Integer delayMinutes

) {
}
