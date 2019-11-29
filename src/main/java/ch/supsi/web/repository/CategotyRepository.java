package ch.supsi.web.repository;


import ch.supsi.web.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategotyRepository extends JpaRepository<Category, String> {}
