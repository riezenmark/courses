package profession;

public class Start {
    public static void main(String[] args) {
        Patient patient = new Patient("Sam", "Toothacke", 32);
        Dentist dentist = new Dentist("Eugen", "Smith", "High", "08.05.1998",
                "New-York Hospital", 100);
        dentist.pullOutTooth(patient);
        System.out.println(patient.getTeeth());
        Diagnosis diagnosis = dentist.heal(patient);
        System.out.println(diagnosis.getName());
        System.out.println(diagnosis.getPatient());
    }
}
