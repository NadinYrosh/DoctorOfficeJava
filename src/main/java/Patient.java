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

 @Override
  public boolean equals(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient =  (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) &&
             this.getId() == newPatient.getId();
            //  this.getDoctorId() == newPatient.getDoctorId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (patient_name, doctor_id) VALUES (:patient_name, :doctor_id)";
      // con.createQuery(sql)
      //   .addParameter("description", this.description)
      //   .executeUpdate();
      this.id = (int) con.createQuery(sql, true)
        .addParameter("patient_name", this.patient_name)
        .addParameter("doctor_id", this.doctor_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE id=:id";
      Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }
}
