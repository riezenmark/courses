package profession;

public class Engineer extends Profession {
    private String plan;

    public Engineer(String name, String surname, String education, String birthday, String plan) {
        super(name, surname, education, birthday);
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void talkToClient(Client client) {
        client.setPlan(plan);
    }
}
