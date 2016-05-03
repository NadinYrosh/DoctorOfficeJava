import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteDoctorsQuery = "DELETE FROM Doctors *;";
      String deletePatientsQuery = "DELETE FROM patients *;";
      String deleteSpecialtiesQuery = "DELETE FROM specialties *;";
      con.createQuery(deleteDoctorsQuery).executeUpdate();
      con.createQuery(deletePatientsQuery).executeUpdate();
      con.createQuery(deleteSpecialtiesQuery).executeUpdate();
    }
  }

  @Test
  public void doctor_instantiatesCorrectly_true() {
    Doctor testDoctor = new Doctor("Jake");
    assertEquals(true, testDoctor instanceof Doctor);
  }

  @Test
  public void getName_doctorInstantiatesNameCorrectly_string() {
    Doctor testDoctor = new Doctor("Jake");
    assertEquals("Jake", testDoctor.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void  equals_returnsTrueIfNamesAreTheSame_true() {
    Doctor firstDoctor = new Doctor("Jake");
    Doctor secondDoctor = new Doctor("Jake");
    assertTrue(firstDoctor.equals(secondDoctor));
  }
}
