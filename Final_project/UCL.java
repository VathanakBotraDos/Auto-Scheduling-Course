// UCL.java
// This class represents the user's collection list of courses
import java.util.ArrayList;

public class UCL {
    private static final double PRICE_PER_HOUR = 0;
    // A list of courses
    public ArrayList<Course> courses;
    // The maximum capacity of the list
    private final int MAX_CAPACITY = 5;

    // A constructor that creates a new empty list
    public UCL() {
        courses = new ArrayList<Course>();
    }

    public double getTotalHours() {
        int totalMinutes = 0;

        for (Course course : courses) {
            String[] scheduleParts = course.getSchedule().split(", ");

            // Assuming two sessions per week
            int sessionsPerWeek = 2;

            for (String schedulePart : scheduleParts) {
                String[] timeParts = schedulePart.substring(4).split("-");

                int startMinutes = toMinutes(timeParts[0]);
                int endMinutes = toMinutes(timeParts[1]);

                // Assuming 15 weeks in a semester
                totalMinutes += (endMinutes - startMinutes) * sessionsPerWeek * 15;
            }
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
        return courses.size();
    }

    // A method that returns the course at a given index in the list
    public Course getCourse(int index) {
        return courses.get(index);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    

    // A method that adds a course to the list if possible
    // Returns true if the course is added, false otherwise
    public boolean addCourse(Course course) {
        // Check if the list is full
        if (courses.size() == MAX_CAPACITY) {
            System.out.print("\u001B[31m");
            System.out.println("The list is full. You cannot add more courses.");
            System.out.print("\u001B[0m");
            return false;
        }
        // Check if the course is already in the list
        if (courses.contains(course)) {
            System.out.print("\u001B[31m");
            System.out.println("The course is already in the list. You cannot add it again.");
            System.out.print("\u001B[0m");
            return false;
        }
        // Check if the course has a schedule conflict with any course in the list
        for (Course c : courses) {
            if (hasScheduleConflict(course, c)) {
                System.out.print("\u001B[31m");
                System.out.println("The course has a schedule conflict with " + c + ". You cannot add it.");
                System.out.print("\u001B[0m");
                return false;
            }
        }
        // If none of the above conditions are met, add the course to the list and
        // return true
        courses.add(course);
        return true;
    }

    // A method that removes a course from the list if possible
    // Returns true if the course is removed, false otherwise
    public boolean removeCourse(Course course) {
        // Check if the list is empty
        if (courses.isEmpty()) {
            System.out.print("\u001B[31m");
            System.out.println("The list is empty. You cannot remove any courses.");
            System.out.print("\u001B[0m");
            return false;
        }
        // Check if the course is in the list
        if (!courses.contains(course)) {
            System.out.print("\u001B[31m");
            System.out.println("The course is not in the list. You cannot remove it.");
            System.out.print("\u001B[0m");
            return false;
        }
        // If none of the above conditions are met, remove the course from the list and
        // return true
        courses.remove(course);
        return true;
    }

    // A method that prints the list
    public void printUCL() {
        System.out.println("\n");
        System.out.print("\u001B[1m");
        System.out.println("User's Collection List:");
        System.out.print("\u001B[0m");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }
        System.out.println("\n");
    }

    // A method that finalizes the list and prints a summary as a formal receipt
    public void finalizeUCL() {
        // Check if the list is empty
        if (courses.isEmpty()) {
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
        System.out.println("Number of courses: " + courses.size());
        System.out.println("Total hours: " + getTotalHours());
        System.out.println("\n");
        System.out.println("Courses:");
        for (Course c : courses) {
            System.out.println(c);
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
        for (Course c : courses) {
            System.out.println(c.getCode() + " - " + c.getName());
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
        for (Course c : courses) {
            if (hasScheduleConflict(course, c)) {
                return true;
            }
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