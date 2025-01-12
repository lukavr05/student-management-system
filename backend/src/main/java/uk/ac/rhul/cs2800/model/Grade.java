package uk.ac.rhul.cs2800.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * The class to store a single Grade.
 */
@Entity
public class Grade {

  @Id
  @GeneratedValue
  Long id;

  @OneToOne
  @JoinColumn(name = "module_code")
  Module module;

  @ManyToOne
  @JoinColumn(name = "student_id")
  Student student;

  private int score;

  /**
   * The non-empty constructor for the Grade class.
   *
   * @param score the score for the Grade.
   */
  public Grade(int score, Module module) {
    this.score = score;
    this.module = module;
  }

  /**
   * Empty constructor for the Grade class.
   */
  public Grade() {
    this.score = -1;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setModule(Module module) {
    this.module = module;
  }

  public int getScore() {
    return this.score;
  }

  public Module getModule() {
    return this.module;
  }

  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }
}
