package generic;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class StoreTest {

    @Test
    public void userStoreTest() {
        AbstractStore<User> userStore = new UserStore(5);
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");
        User user5 = new User("user5");

        userStore.add(user1);
        assertThat(userStore.findById("user1"), is(user1));
        userStore.add(user2);
        assertThat(userStore.findById("user2"), is(user2));
        userStore.add(user3);
        assertThat(userStore.findById("user3"), is(user3));
        userStore.add(user4);
        assertThat(userStore.findById("user4"), is(user4));
        userStore.add(user5);
        assertThat(userStore.findById("user5"), is(user5));

        userStore.delete("user3");
        assertNull(userStore.findById("user3"));

        userStore.replace("user2", user3);
        assertNull(userStore.findById("user2"));
        assertThat(userStore.findById("user3"), is(user3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void userStoreAddExceptionTest() {
        AbstractStore<User> userStore = new UserStore(1);
        User user1 = new User("user1");
        User user2 = new User("user2");

        userStore.add(user1);
        userStore.add(user2);
    }

    @Test
    public void userStoreWhenNotReplacedTest() {
        AbstractStore<User> userStore = new UserStore(1);
        User user1 = new User("user1");

        userStore.add(user1);
        assertThat(userStore.replace("user2", user1), is(false));
    }

    @Test
    public void userStoreWhenNotDeletedTest() {
        AbstractStore<User> userStore = new UserStore(1);
        User user1 = new User("user1");

        userStore.add(user1);
        assertThat(userStore.delete("user2"), is(false));
    }

    @Test
    public void roleStoreTest() {
        AbstractStore<Role> roleStore = new RoleStore(5);
        Role role1 = new Role("role1");
        Role role2 = new Role("role2");
        Role role3 = new Role("role3");
        Role role4 = new Role("role4");
        Role role5 = new Role("role5");

        roleStore.add(role1);
        assertThat(roleStore.findById("role1"), is(role1));
        roleStore.add(role2);
        assertThat(roleStore.findById("role2"), is(role2));
        roleStore.add(role3);
        assertThat(roleStore.findById("role3"), is(role3));
        roleStore.add(role4);
        assertThat(roleStore.findById("role4"), is(role4));
        roleStore.add(role5);
        assertThat(roleStore.findById("role5"), is(role5));

        roleStore.delete("role3");
        assertNull(roleStore.findById("role3"));

        roleStore.replace("role2", role3);
        assertNull(roleStore.findById("role2"));
        assertThat(roleStore.findById("role3"), is(role3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void roleStoreAddExceptionTest() {
        AbstractStore<Role> roleStore = new RoleStore(1);
        Role role1 = new Role("role1");
        Role role2 = new Role("role2");

        roleStore.add(role1);
        roleStore.add(role2);
    }

    @Test
    public void roleStoreWhenNotReplacedTest() {
        AbstractStore<Role> roleStore = new RoleStore(1);
        Role role1 = new Role("role1");

        roleStore.add(role1);
        assertThat(roleStore.replace("role2", role1), is(false));
    }

    @Test
    public void roleStoreWhenNotDeletedTest() {
        AbstractStore<Role> roleStore = new RoleStore(1);
        Role role1 = new Role("role1");

        roleStore.add(role1);
        assertThat(roleStore.delete("role2"), is(false));
    }
}
