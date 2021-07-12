

import java.sql.SQLException;
import java.util.Scanner;
public class Studenti {
    private static int countid=0;
    private int id;
    private String emri;
    private String mbiemri;
    private String city;
    private int [] grades = new int[6];
    private boolean hasPostedFlipgrids;
    int group;

    public Studenti (int id, String emri, String mbiemri, String city,  int group, boolean hasPostedFlipgrids)
    {
        countid++;
        this.id = id;
        this.emri=emri;
        this.mbiemri =mbiemri;
        this.city=city;
        this.hasPostedFlipgrids = hasPostedFlipgrids;
        this.group=group;

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
    public void addToDB ()  {
        String query = "Insert into studentet values(\""+ this.toString()+"\")";

        try {
            ConnectToDB.writetoDB(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
