package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Paper extends BaseEntity<Long>{
    private String title;
    private Long publisher_id;

}
