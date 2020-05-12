package profession;

public class Programmer extends Engineer {

    public Programmer(String name, String surname, String education, String birthday, String plan) {
        super(name, surname, education, birthday, plan);
    }

    public Program writeProgram(String name, int rows) {
        Program program = new Program(name, rows);
        return program;
    }
}
