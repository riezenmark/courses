package profession;

public class Doctor extends Profession {
    private String hospital;

    public Doctor(String name, String surname, String education, String birthday, String hospital) {
        super(name, surname, education, birthday);
        this.hospital = hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public Diagnosis heal(Patient patient) {
        String ilness = patient.getIlness();
        String name = patient.getName();
        Diagnosis diagnosis = new Diagnosis(ilness, name);
        return diagnosis;
    }
}
