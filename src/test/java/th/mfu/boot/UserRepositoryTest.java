package th.mfu.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository repo;

    @Test
    public void testFindByUsername() {
        // Prepare data
        User user1 = new User();
        user1.setUsername("user11");
        user1.setEmail("user11@example.com");
        user1.setDisplayname("User 1");
        user1.setBirthday(LocalDate.of(2000, 1, 1));
        repo.save(user1);

        // Test
        User foundUser = repo.findByUsername("user11");

        // Verify
        assertEquals("user11", foundUser.getUsername());
    }

    @Test
    public void testFindById() {
        // Prepare data
        User user1 = new User();
        user1.setUsername("user11");
        user1.setEmail("user11@example.com");
        user1.setDisplayname("User 1");
        user1.setBirthday(LocalDate.of(2000, 1, 1));
        User savedUser = repo.save(user1);

        // Test
        Optional<User> foundUser = repo.findById(savedUser.getId());

        // Verify
        assertTrue(foundUser.isPresent());
        assertEquals("user11", foundUser.get().getUsername());
    }

     @Test
    public void testDelete() {
        // Prepare data
        User user1 = new User();
        user1.setUsername("user11");
        user1.setEmail("user11@example.com");
        user1.setDisplayname("User 1");
        user1.setBirthday(LocalDate.of(2000, 1, 1));
        User savedUser = repo.save(user1);

        // Test
        repo.delete(savedUser);
        Optional<User> foundUser = repo.findById(savedUser.getId());

        // Verify
        assertFalse(foundUser.isPresent());
    }

}
