package uk.ac.rhul.cs2800.repository;

import org.springframework.data.repository.CrudRepository;
import uk.ac.rhul.cs2800.model.Student;

/**
 * The interface for the Student class.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}
