import java.util.ArrayList;

public class Course{

  private String code;
  private String title;
  private double credit;
  private String grade;
  private String status;
  private String semester;
  private ArrayList<Course> preReqList;

  public Course(){
    code = "N/A";
    title = "N/A";
    credit = 0.5;
    grade = "0";
    status = "Incomplete";
    semester = "N/A";
    preReqList = new ArrayList<Course>();
  }

  public Course(Course c){
    code = c.getCourseCode();
    title = c.getCourseTitle();
    credit = c.getCourseCredit();
    grade = c.getCourseGrade();
    status = c.getCourseStatus();
    semester = c.getSemesterTaken();
    preReqList = new ArrayList<Course>();
  }

  public String getCourseCode(){
      return code;
  }

  public void setCourseCode(String courseCode){
      code = courseCode;
  }

  public String getCourseTitle(){
    return title;
  }

  public void setCourseTitle(String courseTitle){
    title = courseTitle;
  }

  public double getCourseCredit(){
    return credit;
  }

  public void setCourseCredit(double credit){
    this.credit = credit;
  }

  public ArrayList<Course> getPrequisites(){
    return preReqList;
  }

  //To help write prerequisites to file
  public String getPrequisitesCSV(){

    String toReturn = "";

    for(Course c : preReqList){
      toReturn += c.getCourseCode();
      toReturn += ":";
    }

    return toReturn;
  }

  public void setPrerequisites(ArrayList<Course> preReqList){
    this.preReqList = preReqList;
  }

  public void setCourseStatus(String courseStatus){
    status = courseStatus;
  }

  public String getCourseStatus(){
    return status;
  }

  public void setCourseGrade(String grade){
    this.grade = grade;
  }

  public String getCourseGrade(){
    return grade;
  }

  public void setSemesterTake(String semester){
    this.semester = semester;
  }

  public String getSemesterTaken(){
    return semester;
  }

  public String toString(){

    String toReturn;

    toReturn = code + ": " + title + ", " + credit + " credits, " + status + ", " + grade + "%, " + semester;
    return toReturn;
  }
}
