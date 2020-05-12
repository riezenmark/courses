package pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Someone Example Sample");
        student.setGroup("B8118-09.03.02ist");
        student.setAdmission(new Date());

        System.out.println(student.getName() + " of group - " + student.getGroup() + " : " + student.getAdmission());
    }
}
