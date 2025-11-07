import java.sql.*;

public class JDBCdemo {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USER = "root";
    private static final String PASSWORD = "sadiya1234";

    public static void main(String[] args) {
        //try with resourcse
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the DB");
           // insertStudent(conn,"sadiya","sadiya@gmail.com");
            updateStudent(conn,2,"hina","hina@gmail.com");
            selectStudent(conn);
            deleteStudent(conn,1);
            selectStudent(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection conn, String name,String email) {
        String sql="insert into student(name,email) values(' "+ name +" ',' "+ email +"')";
        try(Statement stmt = conn.createStatement()){
            int rows=stmt.executeUpdate(sql);
            System.out.println("INSERTED"+rows);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void selectStudent(Connection conn) {
        String sql="select * from student";
        try(Statement stmt=conn.createStatement()){
            ResultSet resultSet= stmt.executeQuery(sql);
            System.out.println("Student List:");
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String email=resultSet.getString("email");
                String name=resultSet.getString("name");
                System.out.println(email+" "+name+" "+id);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void updateStudent(Connection conn,int id,String name,String email) {
        String sql="update student set name = ' " + name + "',email ='"+ email +"' where id = " + id;
        try(Statement stmt = conn.createStatement()){
            int rows=stmt.executeUpdate(sql);
            System.out.println("UPDATED"+rows);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection conn,int id) {
        String sql="delete from student where id = " + id;
        try(Statement stmt = conn.createStatement()){
            int rows=stmt.executeUpdate(sql);
            System.out.println("DELETED"+rows);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

//public static void main(String[] args) {
//    Connection conn =null;
//    try {
//        conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        System.out.println("Connected to the DB");
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }finally{
//        try {
//            conn.close();
//            System.out.println("Connection closed");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
