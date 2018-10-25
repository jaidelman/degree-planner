import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PlanOfStudy{

  private Degree deg; //Store degree type
  private ArrayList<Course> listOfCourses = new ArrayList<Course>(); //Store list of courses

  public PlanOfStudy(){

  }

  //Sets degree program
  public void setDegreeProgram(Degree deg){
    this.deg = deg;
  }

  //Gets degree program
  public Degree getDegreeProgram(){
    return deg;
  }

  //Imports data from a file
  public void importData(String filename){

    String line = null; //Stores line
    String array[]; //Stores array of strings after split

    try(BufferedReader input = new BufferedReader(new FileReader(filename))){

        while((line = input.readLine()) != null){
          array = line.split(","); //Split on ','

          //Add course, set grade and status based on position in file
          addCourse(array[0], array[3]);
          setCourseGrade(array[0], array[3], array[2]);
          setCourseStatus(array[0], array[3], array[1]);
        }

      input.close();
    }
    catch(FileNotFoundException e){
      System.out.println("ERROR: FILE NOT FOUND");
    }
    catch(IOException e){
      System.out.println("ERROR: IO STREAM INTERUPPTED");
    }
  }

  //Saves state of PoS
  public void saveState(){
    Planner.savePlanOfStudy(this);
  }

  //Adds course to list
  public void addCourse(String courseCode, String semester){
    Course temp = new Course(); //Create new course

    //Set values
    temp.setCourseCode(courseCode);
    temp.setSemesterTaken(semester);

    //Add to list
    listOfCourses.add(temp);
  }

  //Removes course from list
  public void removeCourse(String courseCode, String semester){
    //If exists...
    if(getCourse(courseCode, semester) != null){
      Course c = getCourse(courseCode, semester); //Find it
      listOfCourses.remove(c); //Remove it
    }
  }

  public void setCourseStatus(String courseCode, String semester, String courseStatus){
    //If exists...
    if(getCourse(courseCode, semester) != null){
      Course c = getCourse(courseCode, semester); //Find it
      c.setCourseStatus(courseStatus); //Set course status
    }
  }

  //Gets course status
  public String getCourseStatus(String courseCode){
    //If exists
    if(findCourse(courseCode) != null){
      Course c = findCourse(courseCode); //Find it
      return c.getCourseStatus(); //Get status
    }
    return null; //Otherwise return null
  }

  //Sets course grade
  public void setCourseGrade(String courseCode, String semester, String grade){
    if(getCourse(courseCode, semester) != null){
      Course c = getCourse(courseCode, semester);
      c.setCourseGrade(grade);
    }
  }

  //Gets course from list
  public Course getCourse(String courseCode, String semester){
    for(Course c : listOfCourses){
      if(courseCode.equals(c.getCourseCode()) && semester.equals(c.getSemesterTaken())){
        return c;
      }
    }
    return null;
  }

  //Gets course from list without semester as a parameter
  public Course findCourse(String courseCode){
    for(Course c : listOfCourses){
      if(courseCode.equals(c.getCourseCode())){
        return c;
      }
    }
    return null;
  }

  //Gets list of courses for that insatanc of PoS
  public ArrayList<Course> getListOfCourses(){
    return listOfCourses;
  }

  //Calculates completed credits
  public double viewCompletedCredits(){

    double completedCredits = 0.0;

    //Look through list of courses
    for(Course c : getListOfCourses()){
      if(c.getCourseStatus().equals("Complete")){ //If it's completed
        completedCredits += c.getCourseCredit();  //Add credits to total
      }
    }

    return completedCredits;
  }

  //Overwritten toString()
  public String toString(){
    String toReturn = "";

    for(Course c : listOfCourses){
      toReturn += c.getCourseCode();
      toReturn += ",";
      toReturn += c.getCourseStatus();
      toReturn += ",";
      toReturn += c.getCourseGrade();
      toReturn += ",";
      toReturn += c.getSemesterTaken();
      toReturn += "\n";
    }

    return toReturn;

  }

  //Overwritten isEqual()
  public boolean isEqual(PlanOfStudy a, PlanOfStudy b){
    if(!(a.getListOfCourses() == b.getListOfCourses())){
      return false;
    }
    else if(!(a.getDegreeProgram().equals(b.getDegreeProgram()))){
      return false;
    }
    return true;
  }

}
