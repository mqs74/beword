package pl.mqs.beword.db.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pl.mqs.beword.db.model.User;
import pl.mqs.beword.db.repository.UserRepository;
import pl.mqs.beword.db.test.utils.DataConsts;
import pl.mqs.beword.db.util.ModelConsts;

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

    @BeforeClass
    public static void setUp() {
        userBirthDate = LocalDate.parse(DataConsts.birthDay, DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT));
        firstUser = new User(0,"Jack", "Smith", userBirthDate);
        secondUser = new User(1,"Jan", "Dzban", userBirthDate);
        thirdUser = new User(2,"Kazik", "Grotołazik", userBirthDate);
    }

    @Test
    public void should_find_no_customers_if_repository_is_empty() {
        Iterable<User> customers = repository.findAll();

        assertThat(customers).isEmpty();
    }

    @Test
    public void should_store_a_customer() {
        User user = repository.save(firstUser);

        assertThat(user).hasFieldOrPropertyWithValue("type", firstUser.getType());
        assertThat(user).hasFieldOrPropertyWithValue("firstName", firstUser.getFirstName());
        assertThat(user).hasFieldOrPropertyWithValue("lastName", firstUser.getLastName());
        assertThat(user.getBirthDate().format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT))).isEqualTo(
                firstUser.getBirthDate().format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT)));

        user.toString();
    }

    @Test
    public void should_delete_all_customer() {
        entityManager.persist(firstUser);
        entityManager.persist(secondUser);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

//    @Test
//    public void should_find_all_customers() {
//        entityManager.persist(firstUser);
//        entityManager.persist(secondUser);
//        entityManager.persist(thirdUser);
//
//        Iterable<User> users = repository.findAll();
//
//        assertThat(users).hasSize(3).contains(firstUser, secondUser, thirdUser);
//    }
//
//    @Test
//    public void should_find_customer_by_id() {
//        User customer1 = new User("Jack", "Smith");
//        entityManager.persist(customer1);
//
//        User customer2 = new User("Adam", "Johnson");
//        entityManager.persist(customer2);
//
//        User foundCustomer = repository.findOne(customer2.getId());
//
//        assertThat(foundCustomer).isEqualTo(customer2);
//    }
}
