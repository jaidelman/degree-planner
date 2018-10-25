import java.util.ArrayList;

public abstract class Degree{

   private String degreeTitle;
   protected CourseCatalog catalog;
   protected double creditsRequired;

   public Degree(){
       degreeTitle = "Generic Degree";
       catalog = new CourseCatalog();
   }

   public String getDegreeTitle(){
       return degreeTitle;
   }

   public void setDegreeTitle(String title){
       degreeTitle = title;
   }

   abstract void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes);
   abstract ArrayList<Course> getRequiredCourses();
   abstract boolean meetsRequirements(PlanOfStudy thePlan);
   abstract double numberOfCreditsRemaining(PlanOfStudy thePlan);
   abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);
}
