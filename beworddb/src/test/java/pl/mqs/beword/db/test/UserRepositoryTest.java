package pl.mqs.beword.db.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pl.mqs.beword.db.model.Role;
import pl.mqs.beword.db.model.User;
import pl.mqs.beword.db.util.ModelConsts;
import pl.mqs.beword.db.test.utils.DataConsts;
import pl.mqs.beword.db.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository repository;

    private static LocalDate userBirthDate;
    private static User firstUser;
    private static User secondUser;
    private static User thirdUser;

    @Before
    public void setUp() {
        userBirthDate = LocalDate.parse(DataConsts.birthDay, DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT));
        firstUser = new User("Jack", "Smith", userBirthDate);
        secondUser = new User("Jan", "Dzban", userBirthDate);
        thirdUser = new User("Kazik", "Grotołazik", userBirthDate);
       
        firstUser.addRole(new Role(1, "test", "test role", userBirthDate));
    }

    @Test
    public void should_find_no_customers_if_repository_is_empty() {
        Iterable<User> customers = repository.findAll();

        assertThat(customers).isEmpty();
    }

    @Test
    public void should_store_a_customer() {
        User user = repository.save(firstUser);

        System.out.println(user.toString());
        
        //assertThat(user).hasFieldOrPropertyWithValue("type", firstUser.getType());
        assertThat(user).hasFieldOrPropertyWithValue("firstName", firstUser.getFirstName());
        assertThat(user).hasFieldOrPropertyWithValue("lastName", firstUser.getLastName());
        assertThat(user.getBirthDate().format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT))).isEqualTo(
                firstUser.getBirthDate().format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT)));
    }

    @Test
    public void should_delete_all_customer() {
        entityManager.persist(firstUser);
        entityManager.persist(secondUser);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_customer_by_id() {
        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.persist(thirdUser);

        User foundCustomer = repository.findOne(secondUser.getId());

        assertThat(foundCustomer).isEqualTo(secondUser);
    }
}
