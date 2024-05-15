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

    public StudentDao() {

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
     * get student by id
     * @param id
     * @return result
     */
    public Student findById(int id) {
        for (Student student : findAll()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
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

    /**
     * update student
     * @param student
     * @return status
     */
    public boolean update(Student student) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("update student set name = ?,age=?  where id = ?");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.setInt(3, student.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete student by id
     * @param id
     * @return student
     */
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("delete from student where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
