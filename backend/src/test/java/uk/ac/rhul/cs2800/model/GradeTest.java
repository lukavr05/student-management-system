package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class for testing the Grade class.
 */
public class GradeTest {

  @Test
  // TEST 1 - getters and setters
  void gettersAndSettersTest() {
    Grade grade = new Grade();
    grade.setScore(4);
    assertEquals(4, grade.getScore());
  }

  @Test
  // TEST 2 - Constructor
  void constructorTest() {
    Grade grade2 = new Grade(10, new Module("CS4321", "cs", false));
    assertEquals(10, grade2.getScore());
    assertEquals("CS4321", grade2.getModule().getCode());
  }
}
