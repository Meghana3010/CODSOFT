import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;
    public StudentManagementSystem() {
        students = new ArrayList<>();
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }
    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void writeToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Studentmanagementsystem {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent(sms, scanner);
                    break;
                case 2:
                    removeStudent(sms, scanner);
                    break;
                case 3:
                    searchStudent(sms, scanner);
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    saveToFile(sms, scanner);
                    break;
                case 6:
                    loadFromFile(sms, scanner);
                    break;
                case 7:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private static void addStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student newStudent = new Student(name, rollNumber, grade);
        sms.addStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private static void removeStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter student roll number to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        sms.removeStudent(rollNumber);
        System.out.println("Student removed successfully!");
    }

    private static void searchStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter student roll number to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student foundStudent = sms.searchStudent(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void saveToFile(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter the file name to save data: ");
        String fileName = scanner.nextLine();
        sms.writeToFile(fileName);
        System.out.println("Data saved to file successfully!");
    }

    private static void loadFromFile(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter the file name to load data: ");
        String fileName = scanner.nextLine();
        sms.readFromFile(fileName);
        System.out.println("Data loaded from file successfully!");
    }
}
