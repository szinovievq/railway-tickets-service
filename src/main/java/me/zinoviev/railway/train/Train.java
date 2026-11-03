package me.zinoviev.railway.train;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trains")
@Getter
@Setter
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String number;

    @Column(nullable = false, length = 200)
    private String name;

    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("number ASC")
    private List<Carriage> carriages = new ArrayList<>();

    public void addCarriage(Carriage carriage){
        carriage.setTrain(this);
        carriages.add(carriage);
    }


}
