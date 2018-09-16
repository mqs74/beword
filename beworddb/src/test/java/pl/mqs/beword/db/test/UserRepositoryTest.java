package pl.mqs.beword.db.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.hamcrest.core.IsNull;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import pl.mqs.beword.db.model.Role;
import pl.mqs.beword.db.model.User;
import pl.mqs.beword.db.model.Address;
import pl.mqs.beword.db.model.Credential;
import pl.mqs.beword.db.model.Privilege;
import pl.mqs.beword.db.util.ModelConsts;
import pl.mqs.beword.db.test.utils.DataConsts;
import pl.mqs.beword.db.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository repository;

    private static LocalDate userBirthDate;
    private static User firstUser;
    private static User secondUser;
    private static User thirdUser;
    private static Role testerRole;

    private static Address fooAddress;
    private static Credential fooCredential;
    private static Privilege fooPrivilege;

    @Before
    public void setUp() {
        userBirthDate = LocalDate.parse(DataConsts.birthDay, DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT));
        firstUser = new User("Jack", "Smith", userBirthDate);
        secondUser = new User("Jan", "Dzban", userBirthDate);
        thirdUser = new User("Kazik", "Grotołazik", userBirthDate);

        fooAddress = new Address(5, "Wrzeciono", "8a", "3", "Wejherowo", "09-515", "Polska");

        fooCredential = new Credential(1, "foo");
        fooPrivilege = new Privilege(2, "FOO_PRIVILEGE", "Foo privilege for Bar access", LocalDate.now().plusDays(1));

        testerRole = new Role(1, "test", "test role", userBirthDate);
        testerRole.addCredential(fooCredential);
        testerRole.addPrivilege(fooPrivilege);

        firstUser.addAddress(fooAddress);
        firstUser.addRole(testerRole);
        //firstUser.addCredential(fooCredential);
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

        assertThat(user).hasFieldOrPropertyWithValue("firstName", firstUser.getFirstName());
        assertThat(user).hasFieldOrPropertyWithValue("lastName", firstUser.getLastName());
        assertThat(user.getBirthDate().format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT))).isEqualTo(
                firstUser.getBirthDate().format(DateTimeFormatter.ofPattern(ModelConsts.DATE_FORMAT)));
        assertThat(user.getAddresses().size()).isEqualTo(1);
        assertThat(user.getAddresses()).contains(fooAddress);
        assertThat(user.getCredentials(), IsNull.nullValue());
        assertThat(user.getRoles().size()).isEqualTo(1);
        assertThat(user.getRoles()).contains(testerRole);
        assertThat(user.getRoles().get(0).getCredentials().size()).isEqualTo(1);
        assertThat(user.getRoles().get(0).getCredentials()).contains(fooCredential);
    }

    @Test
    public void should_delete_all_customer() {
        entityManager.persist(firstUser);
        entityManager.persist(secondUser);

//        repository.save(firstUser);
//        repository.save(secondUser);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_customer_by_id() {
        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.persist(thirdUser);

        Optional<User> foundCustomer = repository.findById(secondUser.getId());

        if (!foundCustomer.isPresent())
            Assert.fail();

        assertThat(foundCustomer.get()).isEqualTo(secondUser);
    }
}
