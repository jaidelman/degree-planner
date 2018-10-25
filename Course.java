import java.util.ArrayList;

public class Course{

  private String code;
  private String title;
  private double credit;
  private String grade;
  private String status;
  private String semester;
  private ArrayList<Course> preReqList;
  private String reason; //Required, elective or minor

  public Course(){
    code = "N/A";
    title = "N/A";
    credit = 0.5;
    grade = "0";
    status = "Incomplete";
    semester = "N/A";
    reason = "N/A";
    preReqList = new ArrayList<Course>();
  }

  public Course(Course c){
    code = c.getCourseCode();
    title = c.getCourseTitle();
    credit = c.getCourseCredit();
    grade = c.getCourseGrade();
    status = c.getCourseStatus();
    semester = c.getSemesterTaken();
    reason = c.getCourseReason();
    preReqList = c.getPrequisites();
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

  public void setSemesterTaken(String semester){
    this.semester = semester;
  }

  public String getSemesterTaken(){
    return semester;
  }

  public void setCourseReason(String reason){
    this.reason = reason;
  }

  public String getCourseReason(){
    return reason;
  }

  public String toString(){

    String toReturn;

    toReturn = code + ": " + title + ", " + credit + " credits, " + status + ", " + grade + "%, " + semester;
    return toReturn;
  }

  public boolean isEqual(Course a, Course b){
    if(!(a.getCourseStatus().equals(b.getCourseStatus()))){
      return false;
    }
    else if(!(a.getCourseCode().equals(b.getCourseCode()))){
      return false;
    }
    else if(!(a.getCourseGrade().equals(b.getCourseGrade()))){
      return false;
    }
    else if(!(a.getCourseTitle().equals(b.getCourseTitle()))){
      return false;
    }
    else if(!(a.getCourseCredit() == b.getCourseCredit())){
      return false;
    }
    else if(!(a.getCourseReason().equals(b.getCourseReason()))){
      return false;
    }
    return true;
  }
}
