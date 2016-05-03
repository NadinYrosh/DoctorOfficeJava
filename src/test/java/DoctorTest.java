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
    Doctor testDoctor = new Doctor("Jake", 1);
    assertEquals(true, testDoctor instanceof Doctor);
  }

  @Test
  public void getName_doctorInstantiatesNameCorrectly_string() {
    Doctor testDoctor = new Doctor("Jake", 1);
    assertEquals("Jake", testDoctor.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void  equals_returnsTrueIfNamesAreTheSame_true() {
    Doctor firstDoctor = new Doctor("Jake", 1);
    Doctor secondDoctor = new Doctor("Jake", 1);
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor testDoctor = new Doctor("Jake", 1);
    testDoctor.save();
    assertTrue(Doctor.all().get(0).equals(testDoctor));
  }

  @Test
  public void save_assignsIdToObject() {
    Doctor testDoctor = new Doctor("Jake", 1);
    testDoctor.save();
    Doctor savedDoctor = Doctor.all().get(0);
    assertEquals(testDoctor.getId(), savedDoctor.getId());
  }

  @Test
 public void find_findDoctorInDatabase_true() {
   Doctor testDoctor = new Doctor("Jake", 1);
   testDoctor.save();
   Doctor savedDoctor = Doctor.find(testDoctor.getId());
   assertTrue(testDoctor.equals(savedDoctor));
 }
}
