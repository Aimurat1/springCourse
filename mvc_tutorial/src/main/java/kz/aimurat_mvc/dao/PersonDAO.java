package kz.aimurat_mvc.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import kz.aimurat_mvc.models.Person;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    // private List<Person> people;

    // public PersonDAO() {
    // people = new ArrayList<>();
    // people.add(new Person(++PEOPLE_COUNT, "Alex", "Filipp", "test@gmail.com",
    // 23));
    // people.add(new Person(++PEOPLE_COUNT, "Bob", "Filipp", "test@gmail.com",
    // 34));
    // people.add(new Person(++PEOPLE_COUNT, "Alice", "Filipp", "test@gmail.com",
    // 55));
    // people.add(new Person(++PEOPLE_COUNT, "Tom", "Filipp", "test@gmail.com",
    // 18));
    // }

    public static final String URL = "jdbc:postgresql://localhost:5433/peopleDB";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "qwerty";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person ORDER BY id ASC";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;

    }

    public Person show(int i) {
        Person person = new Person();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
            preparedStatement.setInt(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

            }

            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // People list
        // for (Person p : people) {
        // if (p.getId() == i) {
        // return p;
        // }
        // }
        // return null;

        return null;
    }

    public void add(Person person) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?, ?)");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        // Bad method
        // try {
        // Statement statement = connection.createStatement();
        // String SQL = "INSERT INTO person VALUES(" + 1 + ",'" + person.getName() + "',
        // '" + person.getSurname()
        // + "', " + person.getAge() + ", '" + person.getEmail() + "')";
        // statement.executeUpdate(SQL);

        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        // !People list
        // person.setId(++PEOPLE_COUNT);
        // this.people.add(person);
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE person SET name = ?, surname = ?, age = ?, email = ? WHERE id = ?");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getEmail());

            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Works with the List
        // Person oldPerson = this.show(id);
        // oldPerson.setName(person.getName());
        // oldPerson.setSurname(person.getSurname());
        // oldPerson.setEmail(person.getEmail());
        // oldPerson.setAge(person.getAge());

    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // People list
        // this.people.remove(this.show(id));
    }
}
