import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

class TestContainersTest {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:17");

    @BeforeAll
    static void beforeAll() {
        POSTGRES.start();

    }

    @AfterAll
    static void afterAll() {
        POSTGRES.stop();
    }

    @Test
    public void whenSaveUser() {
        System.out.println("JdbcUrl: " + POSTGRES.getJdbcUrl());
        System.out.println("Username: " + POSTGRES.getUsername());
        System.out.println("Password: " + POSTGRES.getPassword());
    }
}
