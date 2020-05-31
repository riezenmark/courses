package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            this.users.get(user).add(account);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            this.users.get(user).remove(account);
        }
    }

    public List<Account> getUserAccounts(String passport) {
        User user = findByPassport(passport);
        return users.get(user);
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destinationPassport, String destinationRequisite,
                                  double amount) {
        boolean transferred = false;
        if (
                srcPassport != null
                && srcRequisite != null
                && destinationPassport != null
                && destinationRequisite != null
                && amount > 0
        ) {
            final Account srcAccount = getActualAccount(srcPassport, srcRequisite);
            if (srcAccount != null && srcAccount.getValue() >= amount) {
                final Account destinationAccount = getActualAccount(destinationPassport, destinationRequisite);
                if (destinationAccount != null) {
                    srcAccount.setValue(srcAccount.getValue() - amount);
                    destinationAccount.setValue(destinationAccount.getValue() + amount);
                    transferred = true;
                }
            }
        }
        return transferred;
    }

    public Map<User, List<Account>> getUsers() {
        return Map.copyOf(users);
    }

    public Account getActualAccount(String passport, String requisites) {
        Account account = null;
        if (this.getUserAccounts(passport) != null) {
            account = this.getUserAccounts(passport).stream()
                    .filter(
                            acc -> acc.getRequisites().equals(requisites)
                    ).findFirst().orElse(null);
        }
        return account;
    }

    public User findByPassport(String passport) {
        return this.users.keySet().stream()
                .filter(
                        user -> user.getPassport().equals(passport)
                ).findFirst().orElse(null);
    }
}
