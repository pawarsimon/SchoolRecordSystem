/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author simon
 */
public class Student {
    private String firstName;
    private String lastName;
    private String studentNumber;
    private LocalDate dateOfBirth;
    private File imageFile;

    public Student(String firstName, String lastName, String studentNumber, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setStudentNumber(studentNumber);
        setDateOfBirth(dateOfBirth);
        setImageFile(new File("./src/images/default.jpg"));
    }

    public Student(String firstName, String lastName, String studentNumber, LocalDate dateOfBirth, File imageFile) {
        this(firstName, lastName, studentNumber, dateOfBirth);
        setImageFile(imageFile);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null)
        {
            throw new IllegalArgumentException("Please Enter First Name");
        }
        else
            this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(firstName == null)
        {
            throw new IllegalArgumentException("Please Enter Lasr Name");
        }
        else
            this.lastName = lastName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        if(studentNumber == null)
        {
            throw new IllegalArgumentException("Please Enter Student Number");
        }
        else
            this.studentNumber = studentNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    /**
     * This method is to connect to the database
     * @return 
     */
    
    public void connectToDB() throws SQLException{
        Connection conn =  null;
        PreparedStatement preparedStatement = null;
         
        try
        {
            //1.Connect to the database
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es:3306/gc200361569", "gc200361569", "n*-eSANP");
            
            //2.String that holds the query
            String sql = "INSERT INTO students(firstName, lastName, studentNumber, dateOfBirth, imageFile)" 
                            + "VALUES(?,?,?,?,?)";
            
            //3. prepare the sql query
            preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
            
            //4. Date to birthday
            Date db = Date.valueOf(dateOfBirth); 
            
            //Bind value parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,studentNumber);
            preparedStatement.setDate(4,db);
            preparedStatement.setString(5,imageFile.getName());
            
            preparedStatement.executeUpdate();
        }
        
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        finally
        {
            if(preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }
    }
    public String toString(){
        return String.format("s% s% %s", firstName, lastName, studentNumber);
    }
    
    
   
    
    
}
