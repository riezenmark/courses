package profession;

public class Surgeon extends Doctor {
    private String category;

    public Surgeon(String name, String surname, String education, String birthday, String hospital, String category) {
        super(name, surname, education, birthday, hospital);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void operate() {
        System.out.println("Operation in progress...");
        System.out.println("Operation finished successfully");
    }
}
