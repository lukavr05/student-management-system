package uk.ac.rhul.cs2800.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.ac.rhul.cs2800.model.Grade;
import uk.ac.rhul.cs2800.model.Module;
import uk.ac.rhul.cs2800.model.Student;
import uk.ac.rhul.cs2800.repository.GradeRepository;
import uk.ac.rhul.cs2800.repository.ModuleRepository;
import uk.ac.rhul.cs2800.repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The testing class for the Grade Controller.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class GradeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  @Autowired
  private GradeRepository gradeRepository;

  Grade grade;

  /**
   * Testing the addGrade method from the GradeController.
   *
   * @throws JsonProcessingException if the object mapper fails.
   * @throws Exception               if another error occurs.
   */
  @Test
  void addGradeTest() throws JsonProcessingException, Exception {
    // set up test classes
    Student testStudent = new Student(10001, "John", "Pork", "jpork", "johnpork@email.com");
    testStudent = studentRepository.save(testStudent);

    Module testModule = new Module("CS2800", "Software Engineering", false);
    testModule = moduleRepository.save(testModule);

    // set up request body
    Map<String, String> params = new HashMap<String, String>();
    params.put("student_id", Long.toString(testStudent.getId()));
    params.put("module_code", testModule.getCode());
    params.put("score", "40");

    // running the mock mvc to run the test in a simulated environment
    MvcResult action = mockMvc.perform(
            MockMvcRequestBuilders.post("/grades/addGrade").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(params)).accept(MediaType.APPLICATION_JSON))
        .andReturn();
    assertEquals(HttpStatus.OK.value(), action.getResponse().getStatus());

    // returning grade object from object mapper to test values
    Grade testGrade =
        objectMapper.readValue(action.getResponse().getContentAsString(), Grade.class);

    assertEquals(testModule.getCode(), testGrade.getModule().getCode());
    assertEquals(40, testGrade.getScore());
    assertEquals(testStudent.getUsername(), testGrade.getStudent().getUsername());
  }
}
