package nekoder.repositories;

import nekoder.entities.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query(value = "SELECT * from lector where id not in (select lectors_id from department_lectors)", nativeQuery = true)
    List<Lector> findAllWithoutDepartments();

    @Query(value = "SELECT * FROM lector WHERE (first_name like %:query%) OR (last_name like %:query%)  OR (third_name like %:query%) ", nativeQuery = true, name = "query")
    List<Lector> freeSearch(@Param("query") String query);
}
