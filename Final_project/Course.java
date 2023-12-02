// Course.java
// This class represents a course with a code, a name, and a schedule

import java.sql.Time;

public class Course {
    // The code of the course
    private String code;
    // The name of the course
    private String name;
    // The schedule of the course
    private String schedule;

    private Time startTime;
    private Time endTime;

    // Other properties and methods...

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    // A constructor that creates a new course with a given code, name, and schedule
    public Course(String code, String name, String schedule) {
        this.code = code;
        this.name = name;
        this.schedule = schedule;
    }

    // A method that returns the code of the course
    public String getCode() {
        return code;
    }

    // A method that returns the name of the course
    public String getName() {
        return name;
    }

    // A method that returns the schedule of the course
    public String getSchedule() {
        return schedule;
    }

    // A method that returns a string representation of the course
    public String toString() {
        return code + " - " + name + " (" + schedule + ")";
    }

}
