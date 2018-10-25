import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CourseCatalog{

  private static ArrayList<Course> listOfCourses = new ArrayList<Course>();

  public CourseCatalog(){

  }

  public void initializeCatalog(String filename){

    Course preReqCourse; //To see if preReq already exists
    String line = null; //Stores line
    String array[]; //Stores array of strings after split
    ArrayList<Course> preReqList = new ArrayList<Course>(); //To hold the prerequisite list
    Double credit; //To set credit

    try(BufferedReader input = new BufferedReader(new FileReader(filename))){

      while((line = input.readLine()) != null){

        Course temp = new Course();
        Course tempPreReq = new Course();
        preReqList = new ArrayList<Course>();

        array = line.split(",");
        temp.setCourseCode(array[0]);

        if(findCourse(array[0]) != null){
          temp = findCourse(array[0]);
        }
        credit = Double.parseDouble(array[1]);
        temp.setCourseCredit(credit);

        temp.setCourseTitle(array[2]);

        //If there are pre-requisites
        if(array.length == 4){
          array = array[3].split(":");
          for(int i = 0; i < array.length; i++){

              preReqCourse = this.findCourse(array[i]);

              if(preReqCourse == null){
                tempPreReq.setCourseCode(array[i]);
                preReqList.add(tempPreReq);
                listOfCourses.add(tempPreReq);
              }
              else preReqList.add(preReqCourse);

            }
            temp.setPrerequisites(preReqList);
        }

        if(findCourse(temp.getCourseCode()) == null){
          listOfCourses.add(temp);
        }
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

  public void addCourse(Course toAdd){
    listOfCourses.add(toAdd);
  }

  public void removeCourse(Course toRemove){
    listOfCourses.remove(toRemove);
  }

  //To CSV
  public void saveCatalog(){

    String toWrite = "";

    try{
      BufferedWriter w = new BufferedWriter(new FileWriter("bootstrap.csv"));

      for(Course c: listOfCourses){
        toWrite += (c.getCourseCode());
        toWrite += (",");
        toWrite += (String.valueOf(c.getCourseCredit()));
        toWrite += (",");
        toWrite += (c.getCourseTitle());
        toWrite += (",");
        toWrite += (c.getPrequisitesCSV());
        w.write(toWrite.substring(0,toWrite.length()-1));
        w.newLine();
        toWrite = "";
      }
      w.close();
    }
    catch(IOException e){
      System.out.println("ERROR: IOException");
    }

  }

  public Course findCourse(String courseCode){

    for( Course course : listOfCourses ){
      if(courseCode.equals(course.getCourseCode())){
        return course;
      }

    }

    return null;
  }

  public String toString(){
    String toReturn = new String();
    ArrayList<Course> preReqList = new ArrayList<Course>();

    for( Course course: listOfCourses ){
      toReturn += course.toString();
      toReturn += "\n";

      preReqList = course.getPrequisites();
      for(Course c: preReqList){
        toReturn += "   ";
        toReturn += c.toString();
        toReturn += "\n";
      }
    }
    return toReturn;
  }

}
