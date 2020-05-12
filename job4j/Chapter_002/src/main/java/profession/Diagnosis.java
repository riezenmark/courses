package profession;

public class Diagnosis {
    private String name;
    private String patient;

    public Diagnosis(String name, String patient) {
        this.name = name;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public String getPatient() {
        return patient;
    }
}
