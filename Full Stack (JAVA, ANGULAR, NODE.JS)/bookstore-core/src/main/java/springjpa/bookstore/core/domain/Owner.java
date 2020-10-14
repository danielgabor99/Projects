package springjpa.bookstore.core.domain;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Owner extends BaseEntity<Long>{
    private Long ownerId;
    private String oname;
    private String email;
    @OneToMany(mappedBy="owner",cascade = CascadeType.ALL)
    private Set<Animal> animals;
}
