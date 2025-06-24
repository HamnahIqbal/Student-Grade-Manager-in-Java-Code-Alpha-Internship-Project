import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

class Student {
    String name;
    double grade;
    String letterGrade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
        this.letterGrade = calculateLetterGrade(grade);
    }

    private String calculateLetterGrade(double grade) {
        if (grade >= 90) return "A+";
        else if (grade >= 80) return "A";
        else if (grade >= 70) return "B+";
        else if (grade >= 60) return "B";
        else if (grade >= 50) return "C";
        else return "F";
    }
}

public class StudentGradeManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            double grade;
            while (true) {
                System.out.print("Enter numeric grade (0-100): ");
                grade = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                if (grade >= 0 && grade <= 100) break;
                System.out.println("Invalid grade. Please enter a value between 0 and 100.");
            }

            students.add(new Student(name, grade));
        }

        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        Student topStudent = null, bottomStudent = null;

        for (Student s : students) {
            total += s.grade;

            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                bottomStudent = s;
            }
        }

        double average = total / students.size();

        System.out.println("\n----- Summary Report -----");
        System.out.println("Total Students: " + students.size());
        System.out.printf("Average Grade: %.2f (%s)\n", average, calculateLetterGrade(average));

        if (topStudent != null) {
            System.out.printf("Highest Grade: %s (%.2f - %s)\n", topStudent.name, topStudent.grade, topStudent.letterGrade);
        }
        if (bottomStudent != null) {
            System.out.printf("Lowest Grade: %s (%.2f - %s)\n", bottomStudent.name, bottomStudent.grade, bottomStudent.letterGrade);
        }

        Collections.sort(students, Comparator.comparingDouble(s -> -s.grade));

        System.out.println("\nIndividual Student Grades (High to Low):");
        for (Student s : students) {
            System.out.printf("%s: %.2f (%s)\n", s.name, s.grade, s.letterGrade);
        }

        scanner.close();
    }

    private static String calculateLetterGrade(double grade) {
        if (grade >= 90) return "A+";
        else if (grade >= 80) return "A";
        else if (grade >= 70) return "B+";
        else if (grade >= 60) return "B";
        else if (grade >= 50) return "C";
        else return "F";
    }
}