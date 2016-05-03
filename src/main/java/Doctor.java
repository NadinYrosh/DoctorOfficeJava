import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Doctor {
  private int id;
  private String doctor_name;
  private int patient_id;

  public Doctor(String doctor_name, int patient_id) {
    this.doctor_name = doctor_name;
    this.patient_id = patient_id;
  }

  public String getName() {
    return doctor_name;
  }

  public int getId() {
    return id;
  }

  public static List<Doctor> all() {
    String sql = "SELECT id, doctor_name FROM Doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  @Override
  public boolean equals(Object otherDoctor) {
    if (!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getName().equals(newDoctor.getName()) &&
             this.getId() == newDoctor.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Doctors(doctor_name) VALUES (:doctor_name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("doctor_name", this.doctor_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctor find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM doctors WHERE id=:id";
     Doctor doctor = con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetchFirst(Doctor.class);
     return doctor;
   }
 }
}
