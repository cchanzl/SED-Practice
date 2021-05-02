package Q1;

// Answer for 1C
// I allowed for initialisation on loading of the class, hence it is private static final.
// To use the other form of initialization, we need to initialize it under the method getInstance() the first time
// it is called, when instance is null. This is done via an if condition before the method returns instance.

// Answer for 1C
// Using the second pattern would lead to race condition if it is not specified as synchronized, since multiple threads
// may crate multiple instances of MarkingSystem which is undesirable.

import java.util.HashMap;
import java.util.Map;

public class MarkingSystem {

  private final Map<Student, StudentRecord> allStudentMarks;

  private MarkingSystem() {
    allStudentMarks = new HashMap<>();
  }

  private static final MarkingSystem instance = new MarkingSystem();

  public static MarkingSystem getInstance(){return instance;}

  public void registerMark(ExamResult examResult, double scaleFactor) {

    Student student = examResult.getStudent();

    StudentRecord studentRecord;

    if (!allStudentMarks.containsKey(student)) {
      studentRecord = new StudentRecord(student);
      allStudentMarks.put(student, studentRecord);
    } else {
      studentRecord = allStudentMarks.get(student);
    }

    studentRecord.enter(examResult.getCode(), examResult.calculateTotal(scaleFactor));
  }

  public int averageAcrossAllExams(Student student) {

    if (!allStudentMarks.containsKey(student)) {
      throw new RuntimeException("No marks for student: " + student);
    }
    return allStudentMarks.get(student).calculateExamAverage();
  }
}
