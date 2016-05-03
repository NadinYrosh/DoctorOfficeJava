import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Specialty {
  private int id;
  private String specialty;
  private int doctor_id;


  public Specialty(String specialty, int doctor_id) {
    this.specialty = specialty;
    this.doctor_id = doctor_id;
  }

  public String getName() {
    return specialty;
  }

  public int getId() {
    return id;
  }

  public static List<Specialty> all() {
   String sql = "SELECT id, specialty, doctor_id FROM specialties";
   try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Specialty.class);
   }
 }

 public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO specialties (specialty, doctor_id) VALUES (:specialty, :doctor_id)";
     this.id = (int) con.createQuery(sql, true)
       .addParameter("specialty", this.specialty)
       .addParameter("doctor_id", this.doctor_id)
       .executeUpdate()
       .getKey();
   }
 }

 public static Specialty find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM specialties WHERE id=:id";
     Specialty specialty = con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetchFirst(Specialty.class);
     return specialty;
   }
 }
}
