package springjpa.bookstore.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import springjpa.bookstore.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * Created by radu.
 */
@NoRepositoryBean
public interface BookstoreRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
