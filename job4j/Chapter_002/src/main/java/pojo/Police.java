package pojo;

import  java.util.Date;

public class Police {
    public static void main(String[] args) {
        License license = new License();
        license.setOwner("Ivan Ivanov");
        license.setModel("Toyota");
        license.setCode("x101xx");
        license.setCreated(new Date());

        System.out.println(license.getOwner() + " has a " + license.getModel()
                + " with number " + license.getCode() + ". License got on " + license.getCreated());
    }
}
