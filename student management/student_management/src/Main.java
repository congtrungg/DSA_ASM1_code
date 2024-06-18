import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Student {
    private String studentID;
    private String fullName;
    private double mark;

    public Student(String studentID, String fullName, double mark) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.mark = mark;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getRanking() {
        if (mark >= 0 && mark < 5.0) {
            return "Failed";
        } else if (mark >= 5.0 && mark < 6.5) {
            return "Medium";
        } else if (mark >= 6.5 && mark < 7.5) {
            return "Good";
        } else if (mark >= 7.5 && mark < 9.0) {
            return "Very Good";
        } else if (mark >= 9.0 && mark <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid mark";
        }
    }

    @Override
    public String toString() {
        return "StudentID: " + studentID + ", Full Name: " + fullName + ", Mark: " + mark + ", Ranking: " + getRanking();
    }
}

class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Mark: ");
        double mark = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        students.add(new Student(studentID, fullName, mark));
        System.out.println("Student added successfully.");
    }

    public void editStudent() {
        System.out.print("Enter Student ID or Full Name to edit: ");
        String searchQuery = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Student student : students) {
            if (student.getStudentID().toLowerCase().equals(searchQuery) || student.getFullName().toLowerCase().contains(searchQuery)) {
                System.out.print("Enter new Full Name: ");
                student.setFullName(scanner.nextLine());
                System.out.print("Enter new Mark: ");
                student.setMark(scanner.nextDouble());
                scanner.nextLine(); // Consume newline
                System.out.println("Student updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String studentID = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                students.remove(student);
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void sortStudents() {
        System.out.println("Choose sorting method:");
        System.out.println("1. Quick Sort");
        System.out.println("2. Selection Sort");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                quickSortStudents(0, students.size() - 1);
                System.out.println("Students sorted by Quick Sort.");
                break;
            case 2:
                selectionSortStudents();
                System.out.println("Students sorted by Selection Sort.");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Display students after sorting
        displayStudents();
    }

    private void quickSortStudents(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSortStudents(low, pi - 1);
            quickSortStudents(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = students.get(high).getMark();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).getMark() <= pivot) {
                i++;
                Collections.swap(students, i, j);
            }
        }
        Collections.swap(students, i + 1, high);
        return i + 1;
    }

    private void selectionSortStudents() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getMark() < students.get(min_idx).getMark()) {
                    min_idx = j;
                }
            }
            Collections.swap(students, min_idx, i);
        }
    }

    public void searchStudent() {
        System.out.print("Enter Student ID or Full Name to search: ");
        String searchQuery = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Student student : students) {
            if (student.getStudentID().toLowerCase().equals(searchQuery) || student.getFullName().toLowerCase().contains(searchQuery)) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void menu() {
        int choice;
        do {
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student");
            System.out.println("6. Display Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    sortStudents();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    displayStudents();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.menu();
    }
}
