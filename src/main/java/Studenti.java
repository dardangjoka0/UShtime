

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Studenti {
    private static int countid;

    static {
        try {
            countid = getLastId();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private int id;
    private String emri;
    private String mbiemri;
    private String city;
    private int [] grades = new int[6];
    private boolean hasPostedFlipgrids;
    int group;

    private static int getLastId() throws Exception {
        ResultSet rs=ConnectToDB.readFromDB();
        int i=0;
        while(rs.next())
            i=rs.getInt(1);
        return i;
    }
    public Studenti (String emri, String mbiemri, String city,  int group, boolean hasPostedFlipgrids)
    {
        countid++;
        this.id = countid;
        this.emri=emri;
        this.mbiemri =mbiemri;
        this.city=city;
        this.hasPostedFlipgrids = hasPostedFlipgrids;
        this.group=group;
        try {
            readByIndex(this.id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("A new student has been created");


    }
    public static int getCountid()
    {
        return countid;
    }
    private int[]getGrades()
    {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Please provide all the 6 grades:");
        String []subjects={"agile", "scrum", "java", "selenium","cucumber", "API testing"};

        for (int i=0; i<6; i++){
            System.out.println("Please provide provide a grade for "+subjects[i]);
            grades[i]= scanner.nextInt();
        }
        return grades;
    }
    @Override
    public String toString(){

        return this.id +"\", \" "+this.emri+"\", \" "+ this.mbiemri+"\", \" "+this.city+"\" , \"  "+5+"\",  "+ this.hasPostedFlipgrids+" ,\"  "+this.group;

    }
    public static void readAll() throws SQLException {

        ResultSet rs=ConnectToDB.readFromDB();
        int i=1;
        while (rs.next())
        {

            for (int j = 1; j <7 ; j++) {
                System.out.println(rs.getMetaData().getColumnName(j) + ": "+rs.getString(j));

            }
            System.out.println("-------------------");


        }
    }
    public static void readByIndex(int id) throws SQLException {
        ResultSet rs=ConnectToDB.readFromDB(id);


        while (rs.next())
        {

            for (int j = 1; j <7 ; j++) {
                System.out.println(rs.getMetaData().getColumnName(j) + ": "+rs.getString(j));


            }
            System.out.println("-------------------");


        }

    }
    // Write data to DB.
    public void addToDB()  {
        String query = "Insert into studenti values(\""+ this.toString()+"\")";

        try {
            ConnectToDB.writetoDB(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
