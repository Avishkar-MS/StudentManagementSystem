
package com.student;

import com.student.dao.StudentDAO;
import com.student.model.Student;

public class StudentDAOTest {

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // 1. Display existing students
        System.out.println("Before update:");
        for (Student s : dao.getAllStudents()) {
            System.out.println(
                    s.getId() + " | " + s.getName()
                            + " | " + s.getCourse()
            );
        }

        // 2. Update the first student found
        if (dao.getAllStudents().isEmpty()) {
            System.out.println("No students available to test.");
            return;
        }

        Student existing = dao.getAllStudents().get(0);

        Student updated = new Student(
                existing.getId(),
                existing.getName(),
                existing.getAge(),
                existing.getEmail(),
                "Computer Science"
        );

        boolean updateResult = dao.updateStudent(updated);
        System.out.println("Update successful: " + updateResult);

        // 3. Display updated records
        System.out.println("\nAfter update:");
        for (Student s : dao.getAllStudents()) {
            System.out.println(
                    s.getId() + " | " + s.getName()
                            + " | " + s.getCourse()
            );
        }

        // 4. Delete only when you choose to enable this test
        // Uncomment the following lines to test deletion:
        //
        // boolean deleteResult = dao.deleteStudent(existing.getId());
        // System.out.println("Delete successful: " + deleteResult);
    }
}
