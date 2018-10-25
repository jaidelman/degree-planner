
public class Student{

  private String firstName;
  private String lastName;
  private Integer studentNumber;

  public Student(){
    firstName = "";
    lastName = "";
  }

  public String getFullName(){
    String fullName = firstName + " " + lastName;
    return fullName;
  }

  public void setFirstName(String first){
    firstName = first;
  }

  public void setLastName(String last){
    lastName = last;
  }

  public String getFirstName(){
    return firstName;
  }

  public String getLastName(){
    return lastName;
  }

  public void setStudentNumber(Integer studentNum){
    studentNumber = studentNum;
  }

  public Integer getStudentNumber(){
    return studentNumber;
  }

  public String toString(){

    String toReturn = "";

    toReturn += getFullName();
    toReturn += ",";
    toReturn += getStudentNumber();

    return toReturn;
  }

  public boolean isEqual(Student a, Student b){
    if(!(a.getFullName().equals(b.getFullName()))){
      return false;
    }
    else if(!(a.getStudentNumber().equals(b.getStudentNumber()))){
      return false;
    }
    return true;
  }

}
