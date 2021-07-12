import java.sql.SQLException;

public class Klasa1 {
    public static void main(String[] args) throws SQLException {

      Studenti st=new Studenti(9,"bla", "Bla","Bla",12, true);
      Studenti.readAll();
    }
}
