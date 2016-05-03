import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Patient {
  private int id;
  private String patient_name;
  private String birthdate;
  private int doctor_id;

  public Patient(String patient_name, int doctor_id) {
    this.patient_name = patient_name;
    this.doctor_id = doctor_id;
  }

  public String getName() {
    return patient_name;
  }

  public int getId() {
    return id;
  }

  public static List<Patient> all() {
   String sql = "SELECT id, patient_name, doctor_id FROM patients";
   try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Patient.class);
   }
 }
}
