import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PlanOfStudy{

  private Degree deg;
  private ArrayList<Course> listOfCourses = new ArrayList<Course>();

  public PlanOfStudy(){

  }

  public void setDegreeProgram(Degree deg){
    this.deg = deg;
  }

  public Degree getDegreeProgram(){
    return deg;
  }

  public void importData(String filename){

    String line = null; //Stores line
    String array[]; //Stores array of strings after split

    try(BufferedReader input = new BufferedReader(new FileReader(filename))){

        while((line = input.readLine()) != null){
          array = line.split(",");
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

  public void saveState(){

  }

  public void addCourse(String courseCode, String semester){
    Course temp = new Course();

    temp.setCourseCode(courseCode);
    temp.setSemesterTaken(semester);

    listOfCourses.add(temp);
  }

  public void removeCourse(String courseCode, String semester){
    if(getCourse(courseCode, semester) != null){
      Course c = getCourse(courseCode, semester);
      listOfCourses.remove(c);
    }
  }

  public void setCourseStatus(String courseCode, String semester, String courseStatus){
    if(getCourse(courseCode, semester) != null){
      Course c = getCourse(courseCode, semester);
      c.setCourseStatus(courseStatus);
    }
  }

  public void setCourseGrade(String courseCode, String semester, String grade){
    if(getCourse(courseCode, semester) != null){
      Course c = getCourse(courseCode, semester);
      c.setCourseGrade(grade);
    }
  }

  public Course getCourse(String courseCode, String semester){
    for(Course c : listOfCourses){
      if(courseCode.equals(c.getCourseCode()) && semester.equals(c.getSemesterTaken())){
        return c;
      }
    }
    return null;
  }

  public Course getCourse(String courseCode){
    for(Course c : listOfCourses){
      if(courseCode.equals(c.getCourseCode())){
        return c;
      }
    }
    return null;
  }

  public ArrayList<Course> getListOfCourses(){
    return listOfCourses;
  }

  public double viewCompletedCredits(){

    double completedCredits = 0.0;

    for(Course c : getListOfCourses()){
      if(c.getCourseStatus().equals("Complete")){
        completedCredits += c.getCourseCredit();
      }
    }

    return completedCredits;
  }

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

}
