package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The testing class for the Module class.
 */
public class ModuleTest {

  @Test
  // TEST 1 - Getters and Setters
  void gettersAndSettersTest() {
    Module m = new Module();
    
    // testing code setters/getters
    m.setCode("CS2800"); 
    assertEquals("CS2800", m.getCode());

    // testing name setters/getters
    m.setName("Software Engineering");
    assertEquals("Software Engineering", m.getName());

    // testing mnc setters/getters
    m.setMnc(true);
    assertEquals(true, m.getMnc());
  }

  @Test
  // TEST 2 - Constructor
  void constructorTest() {
    // testing non-empty constructor
    Module m1 = new Module("CS1900", "Programming", false);

    assertEquals("CS1900", m1.getCode());
    assertEquals("Programming", m1.getName());
    assertEquals(false, m1.getMnc());
  }

  @Test
  // TEST 3 - Testing equals override
  void equalsTest() {
    Module m2 = new Module("CS3333", "Hello", false);

    // checking the equals method works (true cases)
    assertTrue(m2.equals(m2));
    assertTrue(m2.equals(new Module("CS3333", "Hello", false)));

    // checking the false cases
    assertFalse(m2.equals(new Module("CS3333", "Hello", true)));
    assertFalse(m2.equals(new Module("CS3333", "Goodbye", false)));
    assertFalse(m2.equals(new Module("CS1111", "Hello", true)));
  }

  @Test
  // TEST 4 - testing the hashCode functionality
  void hashCodeTest() {
    Module mod1 = new Module("CS121", "", false);
    Module mod2 = new Module("CS121", "a", true);
    assertEquals(mod1.hashCode(), mod2.hashCode());
  }
}
