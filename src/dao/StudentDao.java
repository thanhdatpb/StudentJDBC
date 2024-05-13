package dao;

import entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static String URL="jdbc:mysql://localhost:3306/studentdb?user=root";
    private static String USER="root";
    private static String PASSWORD="123456";
    private static Connection CONNECTION = getConnection();
    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Select List Student Form DB
     * @return
     */

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        try{
            ResultSet resultSet = CONNECTION.createStatement().executeQuery("select * from student");
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                list.add(student);
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * add student
     * @param student
     * @return status
     */

    public boolean add(Student student) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("insert into student(name, id) values (?, ?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
       StudentDao dao = new StudentDao();
       Student student = new Student();
       student.setId(1);
       student.setName("Thanh Dat");
       student.setAge(21);
       if (dao.add(student)) {
           System.out.println("Add Student Successfully");
       }else {
           System.out.println("Add failed");
       }
    }

}
