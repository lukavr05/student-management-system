package uk.ac.rhul.cs2800.repository;

import org.springframework.data.repository.CrudRepository;
import uk.ac.rhul.cs2800.model.Module;

/**
 * The repository for the Module class.
 */
public interface ModuleRepository extends CrudRepository<Module, String> {
}
