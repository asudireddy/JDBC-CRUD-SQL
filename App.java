import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
      //  System.out.println("Hello, World!");

        

      //steps used for trouble shooting
     // follow rthe steps from link https://stackoverflow.com/questions/12523865/jdbc-simple-mssql-connection-example-not-working
     // Create a new Sql authentication login in Sql server
     // and use connection string with sql authemtcation like String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1;encrypt=true;trustServerCertificate=true";
       
     
     //String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01;Database=Ananya;IntergratedSecurity=true";
        //String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01;Database=Ananya;IntergratedSecurity=true;encrypt=true;trustServerCertificate=true";
        //String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01:1433;Database=Ananya;IntergratedSecurity=true";
        //String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01:1433;Database=Ananya;IntergratedSecurity=true;encrypt=true;trustServerCertificate=true";
        //String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1";

        insertStudent();
        insertStudent2();
        selectStudent();
        updateStudent();
        deleteStudent();
       }

        public static void insertStudent(){
       
        String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1;encrypt=true;trustServerCertificate=true";
        try{
            try(Connection connection = DriverManager.getConnection(conncetionString)){
                System.out.println("Connection established");

                //String sql = "INSERT INTO dbo.Student VALUES (StudentID, LastName, FirstName, Grade) VALUES ('24', 'Sudireddy', 'Venkat Reddy', '12')";
                 String sql = "INSERT INTO dbo.Student(StudtentID, LastName, FirstName, Grade) VALUES('24', 'Sudireddy', 'Venkat Reddy', '12')";
                Statement statement = connection.createStatement();

                //System.out.println(sql);

               int rows = statement.executeUpdate(sql);
                 
                if(rows > 0){
                    System.out.println("A new user has been inserted successfully");
                }
              
            }
        }catch(SQLException ex){
             System.out.println("An error occurred while connecting to the database" + ex);
        }
    }

        

    public static void insertStudent2(){
         int StudtendID = 24;
         String LastName = "Sudireddy";
         String FirstName = "Venkat Reddy";
         int Grade = 12;
        String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1;encrypt=true;trustServerCertificate=true";
        try{
            try(Connection connection = DriverManager.getConnection(conncetionString)){
                System.out.println("Connection established");

                String sql = "INSERT INTO dbo.Student(StudtentID, LastName, FirstName, Grade) VALUES (?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, StudtendID);
                statement.setString(2, LastName);
                statement.setString(3, FirstName);
                statement.setInt(3, Grade);

                int rows = statement.executeUpdate(sql);

                if(rows > 0){
                    System.out.println("A new user has been inserted successfully");
                }
            }
        }catch(SQLException ex){
            System.out.println("An error occurred while connecting to the databade" + ex);
        }
    }

    public static void selectStudent(){
       
        String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1;encrypt=true;trustServerCertificate=true";
            try{
                try(Connection connection = DriverManager.getConnection(conncetionString)){
                      System.out.println("Connection established");

                      String sql = "SELECT * FROM dbo.Student";
                      Statement statement = connection.createStatement();
                      ResultSet result = statement.executeQuery(sql);

                      while(result.next()){
                        int StudtentID = result.getInt("StudtentID");
                        String LastName = result.getString("LastName");
                        String FirstName = result.getString("FirstName");
                        int Grade = result.getInt("Grade");
                        System.out.println(StudtentID + ": " +  LastName + ","  + FirstName  +  ": "  + Grade);
                  }
                  connection.close();
                }
            }catch(SQLException ex){
                System.out.println("An error occurred while connecting to the databade" + ex);
            }
    }


    public static void updateStudent(){

        int StudtendID = 12;
        String LastName = "Park";
        String FirstName = "Giselle";
        int Grade = 9;

        String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1;encrypt=true;trustServerCertificate=true";
            try{
                try(Connection connection = DriverManager.getConnection(conncetionString)){
                   System.out.println("Connection established");
                   System.out.println("A new user has been inserted successfully");
                   String sql = "UPDATE dbo.Student SET StudtentID = ?, LastName = ?, FirstName = ? WHERE Grade = ? ";
                   PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setInt(1,StudtendID);
                   statement.setString(2, LastName);
                   statement.setString(3, FirstName);
                   statement.setInt(4, Grade);
                   

                  int rows =  statement.executeUpdate();

                  if(rows > 0){
                    System.out.println("The user's information has been updated");
                  }

                  connection.close();

                }
            }catch(SQLException ex){
                System.out.println("An error occurred while connecting to the databade" + ex);
            }
    }


    public static void deleteStudent(){
        int StudtendID = 24;

        String conncetionString = "jdbc:sqlserver://DESKTOP-K98SKOS\\\\SQLEXPRESS01:1433;Database=Ananya;user=admin1;password=admin1;encrypt=true;trustServerCertificate=true";
        try{
            try(Connection connection = DriverManager.getConnection(conncetionString)){
                String sql = "DELETE FROM dbo.Student WHERE StudtentID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, StudtendID);

                int rows = statement.executeUpdate();

                if(rows > 0){
                    System.out.println("The user's information has been deleted.");
                }
                connection.close();

            }
        }catch(SQLException ex){
            System.out.println("An error occurred while connecting to the databade" + ex);
        }
    }
}


