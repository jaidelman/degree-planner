import java.util.ArrayList;

public abstract class GeneralDegree extends Degree{

  public GeneralDegree(){
    creditsRequired = 15.0;
  }

  //These are kept abstract in case another GeneralDegree is added
  abstract boolean meetsRequirements(PlanOfStudy thePlan);
  abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);
  abstract void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes);
  abstract ArrayList<Course> getRequiredCourses();

  public double numberOfCreditsRemaining(PlanOfStudy thePlan){
    return creditsRequired - thePlan.viewCompletedCredits();
  }

}
