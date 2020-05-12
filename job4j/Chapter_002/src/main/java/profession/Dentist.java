package profession;

public class Dentist extends Doctor {
    private int price;

    public Dentist(String name, String surname, String education, String birthday, String hospital, int price) {
        super(name, surname, education, birthday, hospital);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void pullOutTooth(Patient patient) {
        patient.setTeeth(patient.getTeeth() - 1);
    }

    public void healTooth(Patient patient) {
        patient.setTeeth(patient.getTeeth() + 1);
    }
}
