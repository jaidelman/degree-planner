import java.util.ArrayList;

public abstract class GeneralDegree extends Degree{

  public GeneralDegree(){

  }

  //These are kept abstract in case another GeneralDegree is added
  abstract boolean meetsRequirements(PlanOfStudy thePlan);
  abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);
  abstract void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes);
  abstract ArrayList<Course> getRequiredCourses();

  public double numberOfCreditsRemaining(PlanOfStudy thePlan){
    return -1;
  }

}
