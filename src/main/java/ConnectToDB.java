import com.mysql.cj.protocol.Resultset;
import org.springframework.lang.Nullable;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
public class ConnectToDB {
    private ConnectToDB()
    {

    }
    private static String username=System.getenv("mysqlusername");
    private static String password=System.getenv("mysqlpassword");

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultset = null;


    // Sinkronizim
    //connection factory
    public  static Connection setConnection (){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicedb",username, password);
            return connection;
        }catch (Exception e){

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return  connection;
    }

    public static Statement setStatement (){

        try {
            statement = setConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }

    public static int writetoDB (String query) throws SQLException {

        return setStatement().executeUpdate(query);
    }

    //Read all from studentet.
    @Nullable
    public static ResultSet readFromDB(){

        try {
            return  setStatement().executeQuery("select * from studenti");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;

        }

    }
    @Nullable
    public static ResultSet readFromDB(int id)
    {
        try {
            return  setStatement().executeQuery("select * from studenti where id="+id);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

//cruda
// stored procedures
// depedency injection
// Https prtokollit
// costum annotation.
