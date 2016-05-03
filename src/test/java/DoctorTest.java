import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {
  // @Before
  // public void setUp() {
  //   DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_office_test", null, null);
  // }
  //
  // @After
  // public void tearDown() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String deleteDoctorsQuery = "DELETE FROM doctors *;";
  //     String deletePatientsQuery = "DELETE FROM patients *;";
  //     String deleteSpecialtiesQuery = "DELETE FROM specialties *;";
  //     con.createQuery(deleteDoctorsQuery).executeUpdate();
  //     con.createQuery(deletePatientsQuery).executeUpdate();
  //     con.createQuery(deleteSpecialtiesQuery).executeUpdate();
  //   }
  // }

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
}