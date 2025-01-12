package uk.ac.rhul.cs2800.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Class representing a module.
 */
@Entity
public class Module {
  @Id
  private String code;

  private String name;
  private boolean mnc;

  /**
   * The non-empty constructor for the Module class.
   *
   * @param code the code for the module.
   * @param name the name of the module.
   * @param mnc whether the module is mandatory non-condonable.
   */
  public Module(String code, String name, boolean mnc) {
    this.code = code;
    this.name = name;
    this.mnc = mnc;
  }

  /**
   * The empty constructor for the Module class.
   */
  public Module() {
    this.code = null;
    this.name = null;
    this.mnc = false;
  }

  // Setters
  public void setCode(String code) {
    this.code = code;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMnc(boolean mnc) {
    this.mnc = mnc;
  }

  // Getters
  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }

  public boolean getMnc() {
    return this.mnc;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    Module m = (Module) other;
    return (this.code.equals(m.getCode()) && this.name.equals(m.getName())
        && this.mnc == m.getMnc());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }

}
