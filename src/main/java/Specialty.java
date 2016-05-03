import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Specialty {
  private int id;
  private String specialty_name;
  private int doctor_id;


  public Doctor(String specialty_name, int doctor_id) {
    this.specialty_name = specialty_name;
    this.doctor_id = doctor_id;
  }
}
