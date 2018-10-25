import java.util.ArrayList;

public class BCG extends GeneralDegree{

  private static ArrayList<Course> requiredCourses = new ArrayList<Course>();

  public BCG(){
    setDegreeTitle("BCG");
  }

  public boolean meetsRequirements(PlanOfStudy thePlan){
    ArrayList<Course> temp = remainingRequiredCourses(thePlan);

    if(temp == null && (thePlan.viewCompletedCredits() >= creditsRequired)){
      return true;
    }
    else{
      System.out.println(temp.toString());
      System.out.println((thePlan.viewCompletedCredits() - creditsRequired) + " credits remaining");
      return false;
    }
  }

  public ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan){

    //Store requiredCourses in temp
    ArrayList<Course> temp = new ArrayList<Course>();
    for(Course c : requiredCourses){
      temp.add(c);
    }

    for(Course c : temp){
      if(thePlan.findCourse(c.getCourseCode()) != null){
        if(thePlan.getCourseStatus(c.getCourseCode()).equals("Complete")){
          temp.remove(c);

        }
      }
    }

    return temp;
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
