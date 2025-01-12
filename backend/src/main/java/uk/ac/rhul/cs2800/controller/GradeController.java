package uk.ac.rhul.cs2800.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.rhul.cs2800.model.Grade;
import uk.ac.rhul.cs2800.model.Module;
import uk.ac.rhul.cs2800.model.Student;
import uk.ac.rhul.cs2800.repository.GradeRepository;
import uk.ac.rhul.cs2800.repository.ModuleRepository;
import uk.ac.rhul.cs2800.repository.StudentRepository;


/**
 * The controller for the Grade class.
 */
@RestController
public class GradeController {
  private final GradeRepository gradeRepository;
  private final StudentRepository studentRepository;
  private final ModuleRepository moduleRepository;

  /**
   * The constructor for the GradeController.
   *
   * @param gradeRepository the injected gradeRepository and studentRepository.
   */
  public GradeController(GradeRepository gradeRepository, StudentRepository studentRepository,
      ModuleRepository moduleRepository) {
    this.gradeRepository = gradeRepository;
    this.studentRepository = studentRepository;
    this.moduleRepository = moduleRepository;
  }

  /**
   * The method for adding a grade to the database.
   *
   * @param params the Map containing the student ID, module code, and score.
   * @return a saved grade repository.
   */
  @PostMapping(value = "/grades/addGrade")
  public ResponseEntity<Grade> addGrade(@RequestBody Map<String, String> params) {
    // request body in format {"key1":"value1", "key2":"value2"}
    // assuming the request will contain {"student_id":"string", "module_code":"string",
    // "score":"string"}

    // convert student ID to Long
    String studentIdString = params.get("student_id");
    Long studentId = Long.parseLong(studentIdString);

    // find the student and the module using the specified values
    Student student = studentRepository.findById(studentId).orElseThrow();
    Module module = moduleRepository.findById(params.get("module_code")).orElseThrow();

    // create the grade and set all values
    Grade grade = new Grade();
    grade.setModule(module);
    grade.setStudent(student);
    grade.setScore(Integer.parseInt(params.get("score")));

    // save the repository
    grade = gradeRepository.save(grade);

    return ResponseEntity.ok(grade);
  }
}
