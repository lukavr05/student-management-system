package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import uk.ac.rhul.cs2800.exceptions.NoGradeAvailableException;
import uk.ac.rhul.cs2800.exceptions.NoRegistrationException;

/**
 * Used for testing the Student class.
 */

public class StudentTest {

  @Test
  // TEST 1 - Getters and Setters
  void gettersAndSettersTest() {
    Student student = new Student();

    // testing ID getting and setting
    student.setId(12345678);
    assertEquals(12345678, student.getId());

    // testing first name getting and setting
    student.setFirstName("John");
    assertEquals("John", student.getFirstName());

    // testing last name getting and setting
    student.setLastName("Fallout");
    assertEquals("Fallout", student.getLastName());

    // testing user name getting and setting
    student.setUserName("jfallout");
    assertEquals("jfallout", student.getUsername());

    // testing email getting and setting
    student.setEmail("jfallout@email.com");
    assertEquals("jfallout@email.com", student.getEmail());
  }

  @Test
  // TEST 2 - Constructor
  void constructorTest() {
    // create a student
    Student student2 = new Student(98765421, "Jane", "Dough", "jdough", "jdamoney@email.com");

    // check all the attributes match
    assertEquals(98765421, student2.getId());
    assertEquals("Jane", student2.getFirstName());
    assertEquals("Dough", student2.getLastName());
    assertEquals("jdough", student2.getUsername());
    assertEquals("jdamoney@email.com", student2.getEmail());
  }

  @Test
  // TEST 3 - Registering a module
  void registerModuleTest() {
    // create a student
    Student student3 = new Student(1290833, "Hello", "World", "helloworld", "hworld@email.com");
    // register a new module
    student3.registerModule(new Module("CS1234", "IT", false));

    // check that the registered_modules list is not empty
    assertNotEquals(0, student3.getRegisteredModules().size());
    // check that the module in the list is the one we added


    // register another module
    student3.registerModule(new Module("CS1111", "Computer", true));
    // check the size of our list is 2 as expected
    assertEquals(2, student3.getRegisteredModules().size());
    // check that the second module in the list is the one we've added
  }

  @Test
  // TEST 4 - Adding a Grade
  void addGradeTest() {
    // create a student
    Student student4 = new Student(1287643, "Bob", "Loblaw", "blobby", "blobster@email.com");
    // create a module to be added to the grade
    Module mod = new Module("CS4012", "Binary", true);
    // create a grade to be added to the student
    Grade grade = new Grade(10, mod);
    // register the module (we don't have/need NoRegistrationFound exception yet)
    student4.registerModule(mod);

    // add the grade to the list of grades
    student4.addGrade(grade);

    // check that the grade list is not empty
    assertNotEquals(0, student4.getGradeList().size());
    // check that the grade is the one we added
    assertEquals(10, student4.getGradeList().get(0).getScore());

    // create another module and register it
    Module mod2 = new Module("CS2222", "Integers", false);
    student4.registerModule(mod2);

    // create another grade
    Grade grade2 = new Grade(3, mod2);

    // add the new grade to the list of grades
    student4.addGrade(grade2);

    // check that the size of the grade list is 2
    assertEquals(2, student4.getGradeList().size());
    // check that the second item is the Grade we just added
    assertEquals(3, student4.getGradeList().get(1).getScore());
  }

  @Test
  // TEST 5 - Getting a grade
  void getGradeTest() throws NoRegistrationException, NoGradeAvailableException {
    // create new student
    Student student5 = new Student(122334, "Harry", "Woods", "hwoods", "woody@email.com");
    // assert that a NoRegistrationException is thrown when we try to access the grade for a
    // module that has not been registered
    assertThrows(NoRegistrationException.class, () -> student5.getGrade(new Module()));

    // create a new module and register it
    Module mod = new Module("CS1111", "IT", false);
    student5.registerModule(mod);

    // check that we throw the NoGradeAvailable exception if the module does not have a grade
    assertThrows(NoGradeAvailableException.class, () -> student5.getGrade(mod));

    // check that our method works as intended and returns the grade for the specified module
    student5.addGrade(new Grade(10, mod));
    assertEquals(10, student5.getGrade(mod).getScore());
  }

  @Test
  // TEST 6 - Computing the average
  void computeAverageTest() throws NoGradeAvailableException {
    // create a new student
    Student student6 = new Student();
    NoGradeAvailableException ex =
        assertThrows(NoGradeAvailableException.class, () -> student6.computeAverage());
    assertEquals("Cannot compute average (no grades available)!", ex.getMessage());

    // add some grades
    student6.addGrade(new Grade(3, new Module()));
    student6.addGrade(new Grade(10, new Module()));
    student6.addGrade(new Grade(2, new Module()));

    // check that we get the correct value for the average
    assertEquals(5.0, student6.computeAverage());

    // add another grade
    student6.addGrade(new Grade(1, new Module()));

    // check that our answer is still correct
    assertEquals(4.0, student6.computeAverage());
  }

  @Test
  // TEST 7 - Testing the NoRegistrationException class
  void noRegistrationExceptionTest() {
    Student student7 = new Student();
    NoRegistrationException ex =
        assertThrows(NoRegistrationException.class, () -> student7.getGrade(new Module()));
    assertEquals("Module not registered!", ex.getMessage());
  }

  @Test
  // TEST 8 - Testing the NoGradeAvailableException cases
  void noGradeAvailableExceptionTest() {
    Student student8 = new Student();
    Module mod = new Module("1234","", false);
    student8.registerModule(mod);

    // check the exception throws correctly when there are no grades
    NoGradeAvailableException ex = 
        assertThrows(NoGradeAvailableException.class, () -> student8.getGrade(mod));
    assertEquals("No grades available!", ex.getMessage());

    // add a grade
    student8.addGrade(new Grade(10, mod));

    // add and register a module
    Module mod2 = new Module();
    student8.registerModule(mod2);

    // check the exception throws correctly when we specify a module that doesn't have a grade
    NoGradeAvailableException ex2 =
        assertThrows(NoGradeAvailableException.class, () -> student8.getGrade(mod2));
    assertEquals("Grade not found for specified module!", ex2.getMessage());
  }

}

