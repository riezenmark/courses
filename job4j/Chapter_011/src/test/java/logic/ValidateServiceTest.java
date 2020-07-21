package logic;

import models.User;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ValidateServiceTest {
    ValidateService logic = ValidateService.INSTANCE;

    @Test
    public void whenAddOneThenStoreHasOne() {
        User user = new User(
                        1,
                        "riezenmark",
                        "riezenmark",
                        "riezenmark@gmail.com",
                        Calendar.getInstance()
                );

        logic.add(user);
        List<User> resultList = logic.getAll();
        User resultUser = logic.findById(Integer.parseInt(user.getId()));

        assertThat(resultList.get(0).getId(), is("1"));
        assertThat(resultList.get(0).getName(), is("riezenmark"));
        assertThat(resultList.get(0).getLogin(), is("riezenmark"));
        assertThat(resultList.get(0).getEmail(), is("riezenmark@gmail.com"));
        assertThat(resultUser.getId(), is("1"));
        assertThat(resultUser.getName(), is("riezenmark"));
        assertThat(resultUser.getLogin(), is("riezenmark"));
        assertThat(resultUser.getEmail(), is("riezenmark@gmail.com"));
    }
}