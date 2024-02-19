package DAO;

import model.ClassStudent;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/studentManager";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";
    private static final String INSERT_STUDENTS_SQL = "INSERT INTO student" + " (name, email, address, c_id) VALUE " +
            " (?, ?, ?, ?);";
    private static final String SELECT_STUDENT_BY_ID = "select id,name,email,address,c_id from student where id =?";
    private static final String SELECT_ALL_STUDENT  = "select s.id, s.name, s.email, s.address, c.name as 'nameClass'\n" +
            "    from student s join class c on c.id = s.c_id;";
    public StudentDAO(){}

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENTS_SQL);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)){
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getC_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Student selectStudent(int id) {
        Student student = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);){
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String c_id = resultSet.getString("c_id");
                student = new Student(name, email, address, c_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> selectAllStudent() {
        List<Student> students = new ArrayList<>();

        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String nameClass = resultSet.getString("nameClass");
                students.add(new Student(id, name, email, address, nameClass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
