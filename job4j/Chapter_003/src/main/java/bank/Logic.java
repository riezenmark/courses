package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        users.get(new User(passport)).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        users.get(new User(passport)).remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return users.get(new User(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String destRequisite,
                                  double amount) {
        boolean transferred = false;
        if (amount > 0) {
            List<Account> srcAccounts = users.get(new User(srcPassport));
            int srcAccountIndex = srcAccounts.indexOf(new Account(srcRequisite));
            if (srcAccountIndex != -1) {
                Account srcAccount = srcAccounts.get(srcAccountIndex);
                if (srcAccount.getValue() >= amount) {
                    List<Account> destAccounts = users.get(new User(destPassport));
                    int destAccountIndex = destAccounts.indexOf(new Account(destRequisite));
                    if (destAccountIndex != -1) {
                        Account destAccount = destAccounts.get(destAccountIndex);
                        srcAccount.setValue(srcAccount.getValue() - amount);
                        destAccount.setValue(destAccount.getValue() + amount);
                        transferred = true;
                    }
                }
            }
        }
        return transferred;
    }

    public Map<User, List<Account>> getUsers() {
        Map<User, List<Account>> copy = new HashMap<>();
        for (User user : users.keySet()) {
            copy.put(user, users.get(user));
        }
        return copy;
    }

}
