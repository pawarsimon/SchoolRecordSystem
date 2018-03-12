
package schoolrecordsystem;

import java.sql.SQLException;
import java.time.LocalDate;
import models.Student;

/**
 *
 * @author simon
 */
public class SchoolRecordSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       
        Student student = new Student("Simon", "Pawar","1", LocalDate.of(1998, 2, 1));
        //System.out.printf("Our new student is %s%n",student);
        
        student.connectToDB();
    }
    
}
