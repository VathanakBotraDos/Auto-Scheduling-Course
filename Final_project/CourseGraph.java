import java.util.*;

public class CourseGraph {
    private Map<Course, List<Edge>> adjCourses;

    public CourseGraph() {
        this.adjCourses = new HashMap<Course, List<Edge>>();
    }

    class Edge {
        int weight;
        Course course;

        public Edge(int weight, Course course) {
            this.weight = weight;
            this.course = course;
        }
    }

    public void addCourse(Course course) {
        adjCourses.putIfAbsent(course, new ArrayList<>());
    }

    public void removeCourse(Course course) {
        adjCourses.values().forEach(e -> e.remove(course));
        adjCourses.remove(course);
    }

    public void addEdge(Course course1, Course course2, int weight) {
        adjCourses.get(course1).add(new Edge(weight, course2));
        adjCourses.get(course2).add(new Edge(weight, course1));
    }

    public void removeEdge(Course course1, Course course2) {
        List<Edge> eV1 = adjCourses.get(course1);
        List<Edge> eV2 = adjCourses.get(course2);
        if (eV1 != null)
            eV1.remove(course2);
        if (eV2 != null)
            eV2.remove(course1);
    }

    public boolean hasScheduleConflict(Course course1, Course course2) {
        // Check if the schedules for course1 and course2 conflict
        if (course1.getEndTime().before(course2.getStartTime()) ||
                course2.getEndTime().before(course1.getStartTime())) {
            // If course1 ends before course2 starts or course2 ends before course1 starts,
            // then there is no conflict
            return false;
        } else {
            // Otherwise, the courses' times overlap and there is a conflict
            return true;
        }
    }

}
