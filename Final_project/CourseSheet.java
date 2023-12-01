
// CourseSheet.java
// This class represents the list of courses with different schedules
import java.util.ArrayList;

public class CourseSheet {
    // A list of courses
    private ArrayList<Course> courses;

    // A constructor that creates a new course sheet with some sample courses
    public CourseSheet() {
        courses = new ArrayList<Course>();
        // Add some sample courses with different schedules
        courses.add(new Course("CS101", "Introduction to Computer Science", "Mon 9:00-10:30, Wed 9:00-10:30"));
        courses.add(new Course("CS102", "Data Structures and Algorithms", "Tue 10:00-11:30, Thu 10:00-11:30"));
        courses.add(new Course("CS103", "Object-Oriented Programming", "Mon 11:00-12:30, Wed 11:00-12:30"));
        courses.add(new Course("CS104", "Database Systems", "Tue 14:00-15:30, Thu 14:00-15:30"));
        courses.add(new Course("CS105", "Artificial Intelligence", "Mon 14:00-15:30, Wed 14:00-15:30"));
        courses.add(new Course("CS106", "Operating Systems", "Tue 16:00-17:30, Thu 16:00-17:30"));
        courses.add(new Course("CS107", "Software Engineering", "Mon 16:00-17:30, Wed 16:00-17:30"));
        courses.add(new Course("CS108", "Computer Networks", "Tue 9:00-10:30, Thu 9:00-10:30"));
        courses.add(new Course("CS109", "Web Development", "Mon 10:00-11:30, Wed 10:00-11:30"));
        courses.add(new Course("CS110", "Machine Learning", "Tue 11:00-12:30, Thu 11:00-12:30"));
    }

    // A method that returns the number of courses in the course sheet
    public int getSize() {
        return courses.size();
    }

    // A method that returns the course at a given index in the course sheet
    public Course getCourse(int index) {
        return courses.get(index);
    }

    // A method that prints the course sheet
    public void printCourseSheet(UCL ucl) {
        System.out.println("Course Sheet:");
        for (int i = 0; i < courses.size(); i++) {
            Course currentCourse = courses.get(i);
            boolean hasConflict = false;
    
            for (Course c : ucl.courses) {
                if (ucl.hasScheduleConflict(currentCourse, c)) {
                    hasConflict = true;
                    break;
                }
            }
    
            if (hasConflict) {
                // Print the course in red if there is a conflict
                System.out.print("\u001B[31m"); // Red color
            }
    
            System.out.println((i + 1) + ". " + currentCourse);
    
            if (hasConflict) {
                // Reset color back to default
                System.out.print("\u001B[0m");
            }
        }
    }

    public boolean hasConflictWithUCL(UCL ucl, Course course) {
        ArrayList<Course> uclCourses = ucl.getCourses();
        for (Course c : uclCourses) {
            if (ucl.hasConflictWithCourse(course)) {
                return true;
            }
        }
        return false;
    }
    
}
