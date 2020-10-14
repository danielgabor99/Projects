package springjpa.bookstore.core.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Orders extends BaseEntity<Long> {
    private Long clientId;
    private Long bookId;
}