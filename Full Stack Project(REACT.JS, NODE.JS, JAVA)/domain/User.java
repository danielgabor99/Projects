package ubb.project.iss.domain;


import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="userTable")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class User  extends BaseEntity<Long>{
    private String first_name;
    private String last_name;
    private String email;
}
