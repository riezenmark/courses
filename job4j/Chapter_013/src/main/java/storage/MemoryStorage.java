package storage;

import models.User;
import org.springframework.stereotype.Component;

@Component
public class MemoryStorage implements Storage {
    @Override
    public void add(User user) {
        System.out.println("User added to memory.");
    }
}
