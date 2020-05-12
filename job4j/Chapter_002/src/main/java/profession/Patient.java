package profession;

public class Patient {
    private String name;
    private String ilness;
    private int teeth;

    public Patient(String name, String ilness, int teeth) {
        this.name = name;
        this.ilness = ilness;
        this.teeth = teeth;
    }

    public String getName() {
        return name;
    }

    public String getIlness() {
        return ilness;
    }

    public int getTeeth() {
        return teeth;
    }

    public void setTeeth(int amount) {
        this.teeth = amount;
    }
}
