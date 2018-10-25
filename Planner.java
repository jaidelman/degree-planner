import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Planner{

  public static Scanner input = new Scanner(System.in); //Instance Scanner
  public static int planNum = 1; //To save different plans in different files

  public static void main(String[] args){

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

    String choice = "1"; //Stores menu choice. String because input.nextInt() kept throwing exceptions

    //Main Menu
    while(Integer.valueOf(choice) > 0){

      System.out.println("Please enter your choice - enter 0 to exit");
      System.out.println("Enter 1 to print your plan of study");
      System.out.println("Enter 2 to select degree and major");
      System.out.println("Enter 3 to add courses to your plan of study and mark their status");
      System.out.println("Enter 4 to mark courses as required, elective, or minor");
      System.out.println("Enter 5 to remove a course");
      System.out.println("Enter 6 to change one of your grades");
      System.out.println("Enter 7 to view a list of required courses that aren't in your plan of study");
      System.out.println("Enter 8 to view a list of prerequisites for any course in your degree and major");
      System.out.println("Enter 9 to view the number of credits you've completed");
      System.out.println("Enter 10 to view the number of credits remaining to complete your plan");
      System.out.println("Enter 11 to determine if you've met the completion requirements for your degree");
      System.out.println("Enter 12 to save your plan of study to a file");
      System.out.println("Enter 13 to load a plan of study from a file");
      choice = input.nextLine();

      //Select Degree
      switch(choice){

        case "1":
          System.out.println(thePlan.toString());
          break;

        //Case Two - Select Degree and Major
        case "2":
          selectMajor(thePlan);
          break;

        //Case Three - Add Courses and Set Status
        case "3":
          addCourse(thePlan);
          break;

        //Case Four - Mark course as required, elective or minor
        case "4":
          markCourseReason(thePlan);
          break;

        //Case Five - Remove Course
        case "5":
          removeCourse(thePlan);
          break;

        //Case Six - Change a grade
        case "6":
          changeGrade(thePlan);
          break;

        //Case Seven - View a list of required courses not in your PoS
        case "7":
          viewRequired(thePlan);
          break;

        //Case 8 - View list of prerequisites for any course
        case "8":
          viewPreReq(thePlan);
          break;

        //Case 9 - View home many credits you've completed
        case "9":
          viewCompletedCredits(thePlan);
          break;

        //Case 10 - View how many credits remaining you have
        case "10":
          viewCreditsRemaining(thePlan);
          break;

        //Case 11 - Determine if you've met the completion requirements
        case "11":
          isCompleted(thePlan);
          break;

        //Case 12 - Save plan of study
        case "12":
          savePlanOfStudy(thePlan);
          break;

        //Case 13 - Load plan of study
        case "13":
          thePlan = loadPlanOfStudy();
          break;

        default:
          break;
      }
      System.out.println();
    }

  }

  public static void selectMajor(PlanOfStudy thePlan){

    String degreeName = ""; //Store degree
    String majorName = ""; //Store major

    //Gets degree, user much choose between BCH and BCG
    while(!degreeName.equals("BCH") && !degreeName.equals("BCG") && !degreeName.equals("BCG")){
      System.out.println("Please enter your degree (BCH or BCG)");
      degreeName = input.nextLine();
    }

    //Gets major
    //If BCH user must select between CS and SEng
    if(degreeName.equals("BCH")){
      while(!majorName.equals("CS") && !majorName.equals("SEng")){
        System.out.println("Please enter your major (CS or SEng)");
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

  }

  //Add course
  public static void addCourse(PlanOfStudy thePlan){

    CourseCatalog catalog = new CourseCatalog();
    Scanner input = new Scanner(System.in);

    String choice = "1"; //Boolean if they want to add another course
    String code, semester, status;

    while(!choice.equals("0")){

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
      choice = input.nextLine();
    }

  }

  //Changes reason for taking course
  public static void markCourseReason(PlanOfStudy thePlan){

    CourseCatalog catalog = new CourseCatalog();

    String code, semester, reason;
    String choice = "1"; //Boolean if they want to mark another course
    Course tempCourse;

    //Loops so user can mark multiple courses
    while(!choice.equals("0")){

      //Gets code and semester, makes temp course
      System.out.println("Please enter the course code of the course you would like to mark");
      code = input.nextLine();
      System.out.println("Please enter the semester of the course you would like to mark");
      semester = input.nextLine();
      tempCourse = thePlan.getCourse(code, semester);

      //Change mark
      if(tempCourse != null){
        System.out.println("Please enter the reason you are taking this course");
        reason = input.nextLine();
        tempCourse.setCourseReason(reason);
      }

      //Prompt for exit
      System.out.println("Please enter 0 to exit, any other number to mark another course");
      choice = input.nextLine();
    }

  }

  //Removes course from plan
  public static void removeCourse(PlanOfStudy thePlan){

    String code, semester;

    System.out.println("Please enter the course code of the course you would like to remove");
    code = input.nextLine();
    System.out.println("Please enter the semester of the course you would like to remove");
    semester = input.nextLine();

    thePlan.removeCourse(code, semester);
  }

  //Changes grade in PoS
  public static void changeGrade(PlanOfStudy thePlan){

    String code, semester, grade;

    //Get code, semester and grade
    System.out.println("Please enter the course code of the course you would like to change");
    code = input.nextLine();
    System.out.println("Please enter the semester of the course you would like to change");
    semester = input.nextLine();
    System.out.println("Please enter the new grade");
    grade = input.nextLine();

    //Set grade
    thePlan.setCourseGrade(code, semester, grade);
  }

  //Save PoS
  public static void savePlanOfStudy(PlanOfStudy thePlan){

    String toWrite;

    try{
      //Use planNum to increment the file name if a user makes 2 different PoS
      BufferedWriter w = new BufferedWriter(new FileWriter("plan" + planNum + ".csv"));

      toWrite = thePlan.toString();

      //Write to the toString to the file
      w.write(toWrite);
      w.close();
      System.out.println("Saved to plan" + planNum + ".csv");

      planNum++; //Increment planNum for next file
    }
    catch(IOException e){
      System.out.println("ERROR: IOException");
    }

  }

  //View required courses
  public static void viewRequired(PlanOfStudy thePlan){

    Degree deg;

    //Find degree
    if(thePlan.getDegreeProgram() == null){
      System.out.println("No degree chosen");
      return;
    }
    else{
      deg = thePlan.getDegreeProgram();
    }

    //Loops through all required courses, if it's not in the plan, print it out
    for(Course c : deg.getRequiredCourses()){
      if(thePlan.findCourse(c.getCourseCode()) == null){
        System.out.println("NOT TAKEN - " + c.toString());
      }
    }
  }

  //View prerequisites for a required course
  public static void viewPreReq(PlanOfStudy thePlan){

    Degree deg;
    String code;
    ArrayList<Course> preReqList = new ArrayList<Course>();

    //Find degree
    if(thePlan.getDegreeProgram() == null){
      System.out.println("No degree chosen");
      return;
    }
    else{
      deg = thePlan.getDegreeProgram();
    }

    //Get course code
    System.out.println("Which course (code) would you like to see the pre-requisites for?");
    code = input.nextLine();

    //Find course, print prereqlist
    for(Course c : deg.getRequiredCourses()){
      if(thePlan.findCourse(code) != null){

        preReqList = c.getPrequisites();

        for(Course i : preReqList){
          System.out.println(i.toString());
        }

      }
    }

  }

  //Views completed credits
  public static double viewCompletedCredits(PlanOfStudy thePlan){
    double completedCredits = thePlan.viewCompletedCredits();
    System.out.println("You have " + completedCredits + " completed credits");
    return completedCredits;
  }

  //Views creditsRemaining
  public static void viewCreditsRemaining(PlanOfStudy thePlan){

    Degree deg;
    double creditsRemaining;

    //Find degree
    if(thePlan.getDegreeProgram() == null){
      System.out.println("No degree chosen");
      return;
    }
    else{
      deg = thePlan.getDegreeProgram();
    }

    //Use accessor to find creditsRemaining
    creditsRemaining = deg.numberOfCreditsRemaining(thePlan);
    System.out.println("Remaining Credits: " + creditsRemaining);
  }

  //Checks if a program is completed
  public static void isCompleted(PlanOfStudy thePlan){

    Degree deg = thePlan.getDegreeProgram();

    //Find degree
    if(thePlan.getDegreeProgram() == null){
      System.out.println("No degree chosen");
      return;
    }
    else{
      deg = thePlan.getDegreeProgram();
    }

    //Check if PoS meet requirements
    if(deg.meetsRequirements(thePlan)){
      System.out.println("Degree is completed");
    }
    else{
      System.out.println("Degree is not completed");
    }

  }

  //Loads plan of study from file
  public static PlanOfStudy loadPlanOfStudy(){

    String filename;
    PlanOfStudy toReturn = new PlanOfStudy();

    //Loads, then imports data
    System.out.println("Please enter the file name to load student transcript");
    filename = input.nextLine();
    toReturn.importData(filename);

    return toReturn;
  }
}
