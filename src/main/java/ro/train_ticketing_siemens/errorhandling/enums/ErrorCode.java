package ro.train_ticketing_siemens.errorhandling.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    _0000_GENERAL_ERROR("An unexpected error occurred"),
    _0001_ACCESS_DENIED("Access denied"),
    _0002_INTERNAL_ERROR("An internal error occurred"),
    _0003_VALIDATION_ERROR("Validation failed: {0}"),
    _0004_CONSTRAINT_VIOLATION("Constraint violation: {0}"),
    _0005_BAD_REQUEST("{0}"),

    _1000_STATION_NOT_FOUND("Station with id {0} not found"),
    _1001_ROUTE_NOT_FOUND("Route with id {0} not found"),
    _1002_ROUTE_STATION_NOT_FOUND("Route station with id {0} not found"),
    _1003_TRAIN_NOT_FOUND("Train with id {0} not found"),
    _1004_SCHEDULE_NOT_FOUND("Schedule with id {0} not found"),
    _1005_BOOKING_NOT_FOUND("Booking with id {0} not found"),

    _2000_ORIGIN_STATION_NOT_ON_ROUTE("Origin station is not on this train route"),
    _2001_DESTINATION_STATION_NOT_ON_ROUTE("Destination station is not on this train route"),
    _2002_INVALID_STATION_ORDER("Origin station must be before destination station"),
    _2003_NOT_ENOUGH_SEATS("Not enough seats. Available seats: {0}"),
    _2004_NO_CONNECTIONS_FOUND("No connections found between the selected stations");

    private final String message;

    public String formatBaseMessage(Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }

        return MessageFormat.format(message, args);
    }
}
