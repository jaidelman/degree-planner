import java.util.ArrayList;

public abstract class HonoursDegree extends Degree{

  public HonoursDegree(){

  }

  abstract boolean meetsRequirements(PlanOfStudy thePlan);
  abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);
  abstract void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes);
  abstract ArrayList<Course> getRequiredCourses();
  
  public double numberOfCreditsRemaining(PlanOfStudy thePlan){
    return -1;
  }

}
