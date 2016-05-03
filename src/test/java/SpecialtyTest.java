import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class SpecialtyTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteDoctorsQuery = "DELETE FROM doctors *;";
      String deletePatientsQuery = "DELETE FROM patients *;";
      String deleteSpecialtiesQuery = "DELETE FROM specialties *;";
      con.createQuery(deleteDoctorsQuery).executeUpdate();
      con.createQuery(deletePatientsQuery).executeUpdate();
      con.createQuery(deleteSpecialtiesQuery).executeUpdate();
    }
  }

  @Test
  public void specialty_instantiatesCorrectly_true() {
    Specialty testSpecialty = new Specialty("dentist", 1);
    assertEquals(true, testSpecialty instanceof Specialty);
  }

  @Test
  public void getName_specialtyWithName_String(){
    Specialty mySpecialty = new Specialty("dentist", 1);
    assertEquals("dentist", mySpecialty.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Specialty.all().size(), 0);
  }

  @Test
  public void save_assignsIdToObject() {
    Specialty mySpecialty = new Specialty("dentist", 1);
    mySpecialty.save();
    Specialty savedSpecialty = Specialty.all().get(0);
    assertEquals(mySpecialty.getId(), savedSpecialty.getId());
  }

  @Test
  public void find_findsSpecialtyInDatabase_True() {
    Specialty mySpecialty = new Specialty("dentist", 1);
    mySpecialty.save();
    Specialty savedSpecialty = Specialty.find(mySpecialty.getId());
    assertTrue(mySpecialty.equals(savedSpecialty));
  }
}
