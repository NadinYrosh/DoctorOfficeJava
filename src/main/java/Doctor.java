import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Doctor {
  private int id;
  private String doctor_name;

  public Doctor(String doctor_name) {
    this.doctor_name = doctor_name;
  }
}
