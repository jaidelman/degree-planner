import java.util.ArrayList;

public abstract class HonoursDegree extends Degree{

  public HonoursDegree(){
    creditsRequired = 20.0;
  }

  abstract boolean meetsRequirements(PlanOfStudy thePlan);
  abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);
  abstract void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes);
  abstract ArrayList<Course> getRequiredCourses();

  public double numberOfCreditsRemaining(PlanOfStudy thePlan){
    return creditsRequired - thePlan.viewCompletedCredits();
  }

  public boolean isEqual(HonoursDegree a, HonoursDegree b){
    if(!(a.getDegreeTitle().equals(b.getDegreeTitle()))){
      return false;
    }
    else if(!(a.getRequiredCourses() == b.getRequiredCourses())){
      return false;
    }
    return true;
  }
}
