import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Bootstrap{

  public static void main(String[] args){

    String filename;
    Scanner input = new Scanner(System.in);

    System.out.println("Please enter the file name to load course list");
    filename = input.nextLine();
    loadCourseList(filename);

    System.out.println("Please enter the file name to load required courses");
    filename = input.nextLine();
    loadRequiredCourses(filename);

    saveFile();

  }

  public static void loadCourseList(String filename){

    String temp;
    CourseCatalog catalog = new CourseCatalog();

    catalog.initializeCatalog(filename);
    temp = catalog.toString();

    //System.out.println(temp);

  }

  public static void loadRequiredCourses(String filename){

    String line;
    String array[];
    ArrayList<String> reqCourses;
    String string;
    Degree sEng;
    Degree cs;
    Degree bcg;

    try(BufferedReader input = new BufferedReader(new FileReader(filename))){

      while((line = input.readLine()) != null){

        reqCourses = new ArrayList<String>();

        array = line.split(",");
        for(int i = 1; i<array.length; i++){
          reqCourses.add(array[i]);
        }
        if(array[0].equals("BCG")){
          bcg = new BCG();
          bcg.setDegreeTitle(array[0]);
          bcg.setRequiredCourses(reqCourses);
          string = bcg.toString();
        }
        else if(array[0].equals("SEng")){
          sEng = new SEng();
          sEng.setDegreeTitle(array[0]);
          sEng.setRequiredCourses(reqCourses);
          string = sEng.toString();
        }
        else if(array[0].equals("CS")){
          cs = new CS();
          cs.setDegreeTitle(array[0]);
          cs.setRequiredCourses(reqCourses);
          string = cs.toString();
        }
        else{
          System.out.println("Degree Type Not Found");
          return;
        }
        //System.out.println(string);
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

  static void saveDegrees(){

    Degree sEng = new SEng();
    Degree cs = new CS();
    Degree bcg = new BCG();
    ArrayList<Course> listOfRequiredCourseCodes = new ArrayList<Course>();
    String toWrite = "";

    try{
      BufferedWriter w = new BufferedWriter(new FileWriter("bootstrap.csv", true));

      //Write BCG
      toWrite += "BCG";
      listOfRequiredCourseCodes = bcg.getRequiredCourses();
      for(Course c : listOfRequiredCourseCodes){
        toWrite += (",");
        toWrite += c.getCourseCode();
      }
      toWrite += "\n";

      //Write CS
      toWrite += "CS";
      listOfRequiredCourseCodes = cs.getRequiredCourses();
      for(Course c : listOfRequiredCourseCodes){
        toWrite += (",");
        toWrite += c.getCourseCode();
      }
      toWrite += "\n";

      //Write SEng
      toWrite += "SEng,";
      listOfRequiredCourseCodes = sEng.getRequiredCourses();
      for(Course c : listOfRequiredCourseCodes){
        toWrite += (",");
        toWrite += c.getCourseCode();
      }
      toWrite += "\n";

      //Write to file
      w.write(toWrite);
      w.newLine();
      w.close();
    }
    catch(IOException e){
      System.out.println("ERROR: IOException");
    }
  }

  static void saveFile(){

    CourseCatalog catalog = new CourseCatalog();

    catalog.saveCatalog();
    saveDegrees();

  }


}
