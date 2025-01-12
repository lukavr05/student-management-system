package uk.ac.rhul.cs2800.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * Class containing a module that a student is registered for.
 */
@Entity
public class Registration {
  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  Student student;

  @OneToOne
  @JoinColumn(name = "module_code")
  Module module;

  /**
   * The constructor for the Registration class.
   *
   * @param module the module to be included in the registration.
   */
  public Registration(Module module) {
    this.module = module;
  }

  public Module getModule() {
    return this.module;
  }


}
