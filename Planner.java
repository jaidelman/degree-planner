import java.util.Scanner;
import java.util.ArrayList;

public class Planner{

  public static void main(String[] args){

    Scanner input = new Scanner(System.in);
    PlanOfStudy thePlan = new PlanOfStudy();
    CourseCatalog catalog = new CourseCatalog();
    String filename;

    //Loads student transcript
    System.out.println("Please enter the file name to load student transcript");
    filename = input.nextLine();
    thePlan.importData(filename);
    //System.out.println(thePlan.toString());

    //Loads CourseCatalog
    catalog.initializeCatalog("catalog.csv");
    //System.out.println(catalog.toString());

    //Loads Degrees
    Bootstrap.loadRequiredCourses("degreeTypes.csv");

    runMenu(thePlan);
    input.close();
  }

  public static void runMenu(PlanOfStudy thePlan){

    int choice = 1; //Stores menu choice
    Scanner input = new Scanner(System.in);

    //Main Menu
    while(choice > 0){

      System.out.println("Please enter your choice - type 0 to exit");
      System.out.println("Type 1 to select degree and major");
      System.out.println("Type 2 to add courses to your plan of study and mark their status");
      choice = input.nextInt();

      //Select Degree
      switch(choice){

        //Case One - Select Degree and Major
        case 1:
          choiceOne(thePlan);
          break;

        //Case 2 - Add Courses and Set Status
        case 2:
          choiceTwo(thePlan);
          break;

        default:
          break;
      }


    }
    input.close();
  }

  public static void choiceOne(PlanOfStudy thePlan){

    Scanner input = new Scanner(System.in);
    String degreeName = ""; //Store degree
    String majorName = ""; //Store major

    //Gets degree, user much choose between BCH and BCG
    while(!degreeName.equals("BCH") || !degreeName.equals("BCG") || !degreeName.equals("BCG")){
      System.out.println("Please enter your degree (BCH or BCG)");
      degreeName = input.nextLine();
    }

    //Gets major
    //If BCH user must select between CS and SEng
    if(degreeName.equals("BCH")){
      while(!majorName.equals("CS") || !majorName.equals("SEng")){
        System.out.println("Please enter your major (CS, BCG or SEng)");
        majorName = input.nextLine();
      }
      if(degreeName.equals("CS")){
        thePlan.setDegreeProgram(new CS());
      }
      else{
        thePlan.setDegreeProgram(new SEng());
      }
    }
    //If BCG select major from all BCG degrees (loops in case more majors are added)
    else{
      while(!majorName.equals("BCG")){
        System.out.println("Please enter your major (BCG)");
        majorName = input.nextLine();
      }
      thePlan.setDegreeProgram(new BCG());
    }
    input.close();
  }

  public static void choiceTwo(PlanOfStudy thePlan){

    CourseCatalog catalog = new CourseCatalog();
    Scanner input = new Scanner(System.in);

    int choice = 1;
    String code;
    String semester;
    String status;


    while(choice != 0){

      //Gets courseCode and Semester
      System.out.println("Please enter the course code you'd like to add");
      code = input.nextLine();
      System.out.println("Please enter the semester (X##)");
      semester = input.nextLine();
      thePlan.addCourse(code, semester);

      //Gets course status
      System.out.println("Please enter " + code + "'s status (InProgress, Complete, Planned)");
      status = input.nextLine();
      thePlan.setCourseStatus(code, semester, status);

      //Asks if user wants to enter new course
      System.out.println("Enter 0 to exit, any other integer to add a new course");
    }
    input.close();
  }

}
