package springjpa.bookstore.core.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * Created by radu.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Book extends BaseEntity<Long> {
    private String bookName;
    private String author;
    private int bookPrice;
}
