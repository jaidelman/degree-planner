import java.util.ArrayList;

public class SEng extends HonoursDegree{

  private static ArrayList<Course> requiredCourses = new ArrayList<Course>();

  public SEng(){
    setDegreeTitle("SEng");
  }

  ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan){
    return null;
  }

  boolean meetsRequirements(PlanOfStudy thePlan){
    return false;
  }

  public void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes){

    for(String s : listOfRequiredCourseCodes){
      if(this.catalog.findCourse(s) != null){
        requiredCourses.add(this.catalog.findCourse(s));
      }

    }
  }

  public ArrayList<Course> getRequiredCourses(){
    return requiredCourses;
  }

  public String toString(){

    String toReturn = new String();

    toReturn += getDegreeTitle();
    toReturn += ":\n";

    for(Course c: requiredCourses){
      toReturn += "   ";
      toReturn += c.toString();
      toReturn += "\n";
    }

    return toReturn;
  }
}
