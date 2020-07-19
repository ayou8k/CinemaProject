package ma.abk.cinema.cinema.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketFrom {
    private String nomClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();
}
