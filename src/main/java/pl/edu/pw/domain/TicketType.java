package pl.edu.pw.domain;

import java.util.Locale;

public enum TicketType {

    ADULT, STUDENT, CHILD;

    public static double ticketCost(TicketType ticketType) {
        return switch (ticketType) {
            case ADULT -> 25.0;
            case STUDENT -> 18.0;
            case CHILD -> 12.5;
            default -> throw new IllegalArgumentException("Wrong ticket type");
        };
    }

    public static TicketType parse(String ticketType) {
        ticketType = ticketType.toLowerCase();
        return switch (ticketType) {
            case "adult" -> ADULT;
            case "student" -> STUDENT;
            case "child" -> CHILD;
            default -> throw new IllegalArgumentException("Wrong ticket type " + ticketType);
        };
    }

}
