package tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                valid = true;
            } catch (MenuOutException moe) {
                moe.printStackTrace();    //full exception log output
                System.out.println("This is not a Key from Menu. Please,"
                        + " input an Integer Number in range from 0 to " + (range.length - 1));
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid Input. This is not an Integer Number. Please, try Again.");
            }
        } while (!valid);
        return value;
    }
}
