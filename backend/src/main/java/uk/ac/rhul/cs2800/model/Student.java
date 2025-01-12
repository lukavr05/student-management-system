package uk.ac.rhul.cs2800.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import uk.ac.rhul.cs2800.exceptions.NoGradeAvailableException;
import uk.ac.rhul.cs2800.exceptions.NoRegistrationException;

/**
 * Class containing all functionality for students.
 */
@Entity
public class Student {

  @Id
  private Long id;

  private String firstName;
  private String lastName;
  private String username;
  private String email;

  @OneToMany
  private List<Grade> grades;

  @OneToMany
  private List<Registration> registered;

  /**
   * The constructor for the Student class.
   *
   * @param id the ID number for a student.
   * @param firstname the first name of a student.
   * @param lastname the last name of a student.
   * @param username the username of a student.
   * @param email the email address of a student.
   */
  public Student(long id, String firstname, String lastname, String username, String email) {
    this.id = id;
    this.firstName = firstname;
    this.lastName = lastname;
    this.username = username;
    this.email = email;
    this.grades = new ArrayList<>();
    this.registered = new ArrayList<>();
  }

  /**
   * The empty constructor for the Student class.
   */
  public Student() {
    this.grades = new ArrayList<>();
    this.registered = new ArrayList<>();
  }

  // Setters
  public void setId(long id) {
    this.id = id;
  }

  public void setFirstName(String firstname) {
    this.firstName = firstname;
  }

  public void setLastName(String lastname) {
    this.lastName = lastname;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // Getters
  public long getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  public List<Registration> getRegisteredModules() {
    return this.registered;
  }

  public List<Grade> getGradeList() {
    return this.grades;
  }

  /**
   * Adds the module of a grade to the list of modules.
   *
   * @param grade a Grade
   */
  public void addGrade(Grade grade) {
    this.grades.add(grade);
  }

  /**
   * computes the average score from the grades stored.
   *
   * @return the average score
   */
  public float computeAverage() throws NoGradeAvailableException {
    // check if there are grades and throw new exception if there are none
    if (this.grades.isEmpty()) {
      throw new NoGradeAvailableException("Cannot compute average (no grades available)!");
    }
    int total = 0;
    int size = this.grades.size();

    // add all the grades
    for (Grade g : this.grades) {
      total += g.getScore();
    }

    // calculate and return the mean
    return total / size;
  }

  /**
   * Method to get a grade given a module.
   *
   * @param module the module to be searched for
   * 
   * @return the grade for the module passed in
   * 
   * @throws NoRegistrationException when the module passed is not registered to the student.
   * @throws NoGradeAvailableException when no grades are available, either because there are none
   *         in the list or because the module has none.
   */
  public Grade getGrade(Module module) throws NoRegistrationException, NoGradeAvailableException {
    // initialise grade
    Grade grade = null;
    boolean isRegistered = false;

    // check if the module is registered
    for (Registration m : this.registered) {
      if (m.getModule().equals(module)) {
        isRegistered = true;
      }
    }

    // throw new exception if the specified module is not registered
    if (!isRegistered) {
      throw new NoRegistrationException("Module not registered!");
    }

    // throw new exception if there are no grades available
    if (grades.isEmpty()) {
      throw new NoGradeAvailableException("No grades available!");
    } else {
      // check if the module has an associated grade
      boolean gradeFlag = false;
      for (Grade g : grades) {
        if (g.getModule().equals(module)) {
          grade = g;
          gradeFlag = true;
        }
      }

      // if there is no associated grade for the module, throw a new exception
      if (!gradeFlag) {
        throw new NoGradeAvailableException("Grade not found for specified module!");
      }
    }

    return grade;
  }

  /**
   * Register a module by adding it to the list of registered modules.
   *
   * @param module the module to be added.
   */
  public void registerModule(Module module) {
    this.registered.add(new Registration(module));
  }

}
