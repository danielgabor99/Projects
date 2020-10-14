package springjpa.bookstore.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Animal extends BaseEntity<Long>{
    private Long animalId;
    private String breed;
    private String aname;
    private int age;
    private String type;
    private int points;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ownerId")
    private Owner owner;
}
