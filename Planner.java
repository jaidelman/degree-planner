import java.util.Scanner;
import java.util.ArrayList;

public class Planner{

  public static void main(String[] args){

    PlanOfStudy thePlan = new PlanOfStudy();
    String filename;
    Scanner input = new Scanner(System.in);

    System.out.println("Please enter the file name to load student transcript");
    filename = input.nextLine();
    thePlan.importData(filename);

    System.out.println(thePlan.toString());
  }

}
