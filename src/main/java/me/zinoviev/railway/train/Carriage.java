package me.zinoviev.railway.train;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carriages")
@Getter
@Setter
@NoArgsConstructor
public class Carriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CarriageType type;

    @OneToMany(
            mappedBy = "carriage",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("number ASC")
    private List<Seat> seats = new ArrayList<>();

    public void generateSeats(){

        for (int number=1; number <= type.getSeatCount(); number++){
            Seat seat = new Seat();
            seat.setNumber(number);
            seat.setCarriage(this);
            seats.add(seat);
        }

    }

}
