package bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogicTest {
    Logic logic = new Logic();

    @Test
    public void whenAddUserThenMapHasUser() {
        User user = new User("Sam", "1232 0564");
        logic.addUser(user);
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(user, new ArrayList<>());
        assertThat(logic.getUsers(), is(expected));
    }

    @Test
    public void whenAdd2UsersThenMapHas2Users() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(user1, new ArrayList<>());
        expected.put(user2, new ArrayList<>());
        assertThat(logic.getUsers(), is(expected));
    }

    @Test
    public void whenDeleteUsersThenMapHas1User() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.deleteUser(user2);
        assertThat(logic.getUsers().size(), is(1));
    }

    @Test
    public void whenAddAccountsToUserThenUserHasAccounts() {
        User user = new User("Sam", "1232 0564");
        logic.addUser(user);
        Account account1 = new Account(50000, "343262361423");
        Account account2 = new Account(300000, "123981300098");
        logic.addAccountToUser("1232 0564", account1);
        logic.addAccountToUser("1232 0564", account2);
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(user, new ArrayList<>(Arrays.asList(
                new Account(50000, "343262361423"),
                new Account(300000, "123981300098")
        )));
        assertThat(logic.getUsers(), is(expected));
    }

    @Test
    public void whenDeleteAccountsFromUserThenUserHasNoAccounts() {
        User user = new User("Sam", "1232 0564");
        logic.addUser(user);
        Account account1 = new Account(50000, "343262361423");
        Account account2 = new Account(300000, "123981300098");
        logic.addAccountToUser("1232 0564", account1);
        logic.addAccountToUser("1232 0564", account2);
        logic.deleteAccountFromUser("1232 0564",
                new Account(50000, "343262361423"));
        logic.deleteAccountFromUser("1232 0564",
                new Account(300000, "123981300098"));
        assertThat(logic.getUsers().get(user).isEmpty(), is(true));
    }

    @Test
    public void whenGetUserAccountsThenUserAccounts() {
        User user = new User("Sam", "1232 0564");
        logic.addUser(user);
        Account account1 = new Account(50000, "343262361423");
        Account account2 = new Account(300000, "123981300098");
        logic.addAccountToUser("1232 0564", account1);
        logic.addAccountToUser("1232 0564", account2);
        List<Account> expected = new ArrayList<>(Arrays.asList(
                new Account(50000, "343262361423"),
                new Account(300000, "123981300098")
        ));
        assertThat(logic.getUserAccounts("1232 0564"), is(expected));
    }

    @Test
    public void whenTransferMoneyToAnotherUserAccount() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        logic.transferMoney(
                "1232 0564", "343262361423",
                "6349 0872", "123981300098",
                20000);
        double value1 = logic.getUserAccounts("1232 0564").get(0).getValue();
        double value2 = logic.getUserAccounts("6349 0872").get(0).getValue();
        double[] values = {value1, value2};
        double[] expected = {30000, 320000};
        assertThat(values, is(expected));
    }

    @Test
    public void whenTransferMoneyToSameUserAccount() {
        User user1 = new User("Sam", "1232 0564");
        logic.addUser(user1);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("1232 0564", new Account(300000, "123981300098"));
        logic.transferMoney(
                "1232 0564", "343262361423",
                "1232 0564", "123981300098",
                20000);
        double value1 = logic.getUserAccounts("1232 0564").get(0).getValue();
        double value2 = logic.getUserAccounts("1232 0564").get(1).getValue();
        double[] values = {value1, value2};
        double[] expected = {30000, 320000};
        assertThat(values, is(expected));
    }

    @Test
    public void whenNotEnoughMoneyToTransfer() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        boolean transferred = logic.transferMoney(
                "1232 0564", "343262361423",
                "6349 0872", "123981300098",
                70000);
        assertThat(transferred, is(false));
    }

    @Test
    public void whenThereIsNoSuchSourceUserThenFalse() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        boolean transferred = logic.transferMoney(
                "error", "343262361423",
                "6349 0872", "123981300098",
                30000);
        assertThat(transferred, is(false));
    }

    @Test
    public void whenThereIsNoSuchSourceAccount() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        boolean transferred = logic.transferMoney(
                "1232 0564", "error",
                "6349 0872", "123981300098",
                30000);
        assertThat(transferred, is(false));
    }

    @Test
    public void whenThereIsNoSuchDestinationUserThenFalse() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        boolean transferred = logic.transferMoney(
                "1232 0564", "343262361423",
                "error", "123981300098",
                30000);
        assertThat(transferred, is(false));
    }

    @Test
    public void whenThereIsNoSuchDestinationAccount() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        boolean transferred = logic.transferMoney(
                "1232 0564", "343262361423",
                "6349 0872", "error",
                30000);
        assertThat(transferred, is(false));
    }

    @Test
    public void whenNegativeAmount() {
        User user1 = new User("Sam", "1232 0564");
        User user2 = new User("Ian", "6349 0872");
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addAccountToUser("1232 0564", new Account(50000, "343262361423"));
        logic.addAccountToUser("6349 0872", new Account(300000, "123981300098"));
        boolean transferred = logic.transferMoney(
                "1232 0564", "343262361423",
                "6349 0872", "123981300098",
                -30000);
        assertThat(transferred, is(false));
    }
}
