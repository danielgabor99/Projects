package ubb.project.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public interface Repository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
