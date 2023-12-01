
// This class represents the user's collection list of courses
import java.util.ArrayList;

public class UCL {

    private Node head; // head of the linked list

    private static final double PRICE_PER_HOUR = 0;

    private final int MAX_CAPACITY = 5;

    public UCL() {
        head = null; // initialize head as null
    }

    // Node class for linked list
    public static class Node {
        Course course;
        Node next;

        Node(Course course) {
            this.course = course;
            this.next = null;
        }
    }

    // A method that returns the head of the linked list
    public Node getHead() {
        return head;
    }

    private boolean dfs(Course course) {
        Node current = head;
        while (current != null) {
            if (current.course.equals(course)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public double getTotalHours() {
        int totalMinutes = 0;
        Node current = head;

        while (current != null) {
            String[] scheduleParts = current.course.getSchedule().split(", ");

            // Assuming two sessions per week
            int sessionsPerWeek = 2;

            for (String schedulePart : scheduleParts) {
                String[] timeParts = schedulePart.substring(4).split("-");

                int startMinutes = toMinutes(timeParts[0]);
                int endMinutes = toMinutes(timeParts[1]);

                // Assuming 15 weeks in a semester
                totalMinutes += (endMinutes - startMinutes) * sessionsPerWeek * 15;
            }
            current = current.next;
        }

        // Convert total minutes to hours
        return totalMinutes / 60.0;
    }

    public double getTotalAmount() {
        return getTotalHours() * PRICE_PER_HOUR;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // A method that returns the number of courses in the list
    public int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // A method that returns the course at a given index in the list
    public Course getCourse(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.course;
            }
            count++;
            current = current.next;
        }
        return null; // return null if index is out of bounds
    }

    public boolean addCourse(Course course) {
        // Check if the list is full
        Node current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        if (count == MAX_CAPACITY) {
            System.out.print("\u001B[31m");
            System.out.println("The list is full. You cannot add more courses.");
            System.out.print("\u001B[0m");
            return false;
        }
        // Check if the course is already in the list
        current = head;
        while (current != null) {
            if (current.course.equals(course)) {
                System.out.print("\u001B[31m");
                System.out.println("The course is already in the list. You cannot add it again.");
                System.out.print("\u001B[0m");
                return false;
            }
            current = current.next;
        }
        // Check if the course has a schedule conflict with any course in the list
        current = head;
        while (current != null) {
            if (hasScheduleConflict(course, current.course)) {
                System.out.print("\u001B[31m");
                System.out
                        .println("The course has a schedule conflict with " + current.course + ". You cannot add it.");
                System.out.print("\u001B[0m");
                return false;
            }
            current = current.next;
        }
        // Add the course to the linked list
        Node newNode = new Node(course);
        if (head == null) {
            head = newNode;
        } else {
            current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        return true;
    }

    public boolean removeCourse(Course course) {
        // Check if the list is empty
        if (head == null) {
            System.out.print("\u001B[31m");
            System.out.println("The list is empty. There are no courses to remove.");
            System.out.print("\u001B[0m");
            return false;
        }
        // Check if the course is at the head of the list
        if (head.course.equals(course)) {
            head = head.next;
            return true;
        }
        // Check the rest of the list
        Node current = head;
        while (current.next != null) {
            if (current.next.course.equals(course)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        // The course was not found in the list
        System.out.print("\u001B[31m");
        System.out.println("The course is not in the list. You cannot remove it.");
        System.out.print("\u001B[0m");
        return false;
    }

    // A method that prints the list
    // A method that prints the list
    public void printUCL() {
        System.out.println("\n");
        System.out.print("\u001B[1m");
        System.out.println("User's Collection List:");
        System.out.print("\u001B[0m");
        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println((i++) + ". " + current.course);
            current = current.next;
        }
        System.out.println("\n");
    }

    // A method that finalizes the list and prints a summary as a formal receipt
    public void finalizeUCL() {
        // Check if the list is empty
        if (head == null) {
            System.out.print("\u001B[31m");
            System.out.println("The list is empty. You cannot finalize it.");
            System.out.print("\u001B[0m");
            return;
        }
        // Print a summary of the list
        System.out.println("==================================");
        System.out.print("\u001B[1m");
        System.out.println("Summary of User's Collection List:");
        System.out.print("\u001B[0m");
        Node current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        System.out.println("Number of courses: " + count);
        System.out.println("Total hours: " + getTotalHours());
        System.out.println("\n");
        System.out.println("Courses:");
        current = head;
        while (current != null) {
            System.out.println(current.course);
            current = current.next;
        }
        System.out.println("\n");
        // Print a formal receipt
        System.out.print("\u001B[1m");
        System.out.println("Formal Receipt:");
        System.out.print("\u001B[0m");
        System.out.println("Thank you for choosing our courses. Here is your formal receipt.");
        System.out.println("Date: " + java.time.LocalDate.now());
        System.out.println("Name: " + java.lang.System.getProperty("user.name"));
        System.out.println("\n");
        System.out.print("\u001B[1m");
        System.out.println("Courses:");
        System.out.print("\u001B[0m");
        current = head;
        while (current != null) {
            System.out.println(current.course.getCode() + " - " + current.course.getName());
            current = current.next;
        }
        System.out.println("\n");
        System.out.print("\u001B[32m"); // Start of green color
        System.out.println("Total amount: " + getTotalAmount() + " USD");
        System.out.println("Payment method: Credit Card");
        System.out.print("\u001B[0m");

        System.out.print("\u001B[31m");
        System.out.println("Please keep this receipt for your reference.");
        System.out.print("\u001B[0m");
        System.out.println("==================================");
    }

    public boolean hasConflictWithCourse(Course course) {
        Node current = head;
        while (current != null) {
            if (hasScheduleConflict(course, current.course)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // A helper method that checks if two courses have a schedule conflict
    // Returns true if they have a conflict, false otherwise
    public boolean hasScheduleConflict(Course c1, Course c2) {
        // Split the schedules of the courses by commas
        String[] s1 = c1.getSchedule().split(", ");
        String[] s2 = c2.getSchedule().split(", ");
        // Loop through the schedules of the courses and compare them
        for (String t1 : s1) {
            for (String t2 : s2) {
                // If the schedules have the same day, check the time intervals
                if (t1.substring(0, 3).equals(t2.substring(0, 3))) {
                    // Split the time intervals by dashes
                    String[] i1 = t1.substring(4).split("-");
                    String[] i2 = t2.substring(4).split("-");
                    // Convert the time intervals to minutes
                    int start1 = toMinutes(i1[0]);
                    int end1 = toMinutes(i1[1]);
                    int start2 = toMinutes(i2[0]);
                    int end2 = toMinutes(i2[1]);
                    // Check if the time intervals overlap
                    if (start1 < end2 && start2 < end1) {
                        return true;
                    }
                }
            }
        }
        // If none of the schedules have a conflict, return false
        return false;
    }
}