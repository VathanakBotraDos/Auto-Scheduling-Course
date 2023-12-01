import java.util.Scanner;

public class Main {
    // A scanner object to read the user input
    private static Scanner scanner = new Scanner(System.in);

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        // Create a new course sheet object
        CourseSheet courseSheet = new CourseSheet();
        // Create a new user's collection list object
        UCL ucl = new UCL();
        // Print a welcome message
        System.out.println("=======================================");
        System.out.print("\u001B[1m");
        System.out.println("Welcome to the course selection program.");
        System.out.print("\u001B[0m");
        System.out.println("\n");
        // Show the three options to the user until they choose to exit
        boolean exit = false;
        while (!exit) {
            clearConsole();
            // Print the course sheet
            courseSheet.printCourseSheet(ucl);
            // Print the user's collection list
            ucl.printUCL();
            // Print the three options
            System.out.print("\u001B[1m");
            System.out.println("Please choose one of the following options:");
            System.out.print("\u001B[0m");
            System.out.println("1. Add a course to your list");
            System.out.println("2. Delete a course from your list");
            System.out.println("3. Finalize your list and make a summary as a formal receipt");
            System.out.println("4. Exit the program");
            System.out.println("=======================================");
            // Read the user's choice
            int choice = readInt(1, 4);

            // Perform the corresponding action
            switch (choice) {
                case 1:
                    // Add a course to the list
                    addCourse(courseSheet, ucl);
                    break;
                case 2:
                    // Delete a course from the list
                    deleteCourse(ucl);
                    break;
                case 3:
                    // Finalize the list and make a summary as a formal receipt
                    ucl.finalizeUCL();
                    exit = true; // Set exit to true to end the loop
                    break;
                case 4:
                    // Exit the program
                    exit = true;
                    break;
            }

            // Prompt the user to press Enter to continue
            System.out.print("\u001B[32m"); // Start of green color
            System.out.println("Press Enter to continue...");
            System.out.print("\u001B[0m");
            scanner.nextLine(); // Wait for the Enter key press

        }
        // Print a goodbye message
        System.out.println("Thank you for using the course selection program. Goodbye.");
        System.out.print("\u001B[32m"); // Start of green color
        System.out.println("Press Enter to continue...");
        System.out.print("\u001B[0m");
        scanner.nextLine(); // Wait for the Enter key press
        clearConsole();

    }

    // A helper method that reads an integer from the user within a given range
    // Returns the integer if it is valid, otherwise prompts the user again
    private static int readInt(int min, int max) {
        // Loop until a valid integer is entered
        while (true) {
            // Try to parse the user input as an integer
            try {
                int input = Integer.parseInt(scanner.nextLine());
                // Check if the input is within the range
                if (input >= min && input <= max) {
                    // Return the input
                    return input;
                } else {
                    // Print an error message
                    System.out.println("Invalid input. Please enter an integer between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                // Print an error message
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    // A helper method that adds a course to the user's collection list
    private static void addCourse(CourseSheet courseSheet, UCL ucl) {
        // Print a message
        System.out.println("Please enter the number of the course you want to add to your list.");
        // Read the user's choice
        int choice = readInt(1, courseSheet.getSize());
        // Get the course from the course sheet
        Course course = courseSheet.getCourse(choice - 1);
        // Try to add the course to the user's collection list
        boolean added = ucl.addCourse(course);
        // Print a message based on the result
        if (added) {
            System.out.println("The course " + course + " has been added to your list.");
        } else {
            System.out.println("The course " + course + " could not be added to your list.");
        }
    }

    // A helper method that deletes a course from the user's collection list
    private static void deleteCourse(UCL ucl) {
        // Print a message
        System.out.println("Please enter the number of the course you want to delete from your list.");
        // Read the user's choice
        int choice = readInt(1, ucl.getSize());
        // Get the course from the user's collection list
        Course course = ucl.getCourse(choice - 1);
        // Try to remove the course from the user's collection list
        boolean removed = ucl.removeCourse(course);
        // Print a message based on the result
        if (removed) {
            System.out.println("The course " + course + " has been removed from your list.");
        } else {
            System.out.println("The course " + course + " could not be removed from your list.");
        }
    }
}
