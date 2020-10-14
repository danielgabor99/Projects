package ubb.project.iss.domain;

import lombok.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Conference extends BaseEntity<Long>{

    /*private List<User> sc_members_id;

    private List<String> topics;

    private List<Integer> number_of_seats;*/

    private LocalDate starting_date;

    private LocalDate paper_deadline;
}

