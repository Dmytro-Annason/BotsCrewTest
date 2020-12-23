package nekoder.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nekoder.entities.enums.Degree;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String thirdName;
    private int salary;

    @Enumerated(EnumType.ORDINAL)
    private Degree degree;

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + thirdName;
    }
}
