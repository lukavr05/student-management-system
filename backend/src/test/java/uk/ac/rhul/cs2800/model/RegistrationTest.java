package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class for testing the Registration class.
 */
public class RegistrationTest {

  @Test
  // TEST 1 - getters and setters
  void settersAndGettersTest() {
    Module mod = new Module();
    Registration reg = new Registration(mod);
    assertEquals(mod, reg.getModule());
  }

  @Test
  // TEST 2 - Constructor Test
  void constructorTest() {
    Registration reg1 = new Registration(new Module("CS1234", "Computers", true));
    assertEquals("CS1234", reg1.getModule().getCode());
  }
}
