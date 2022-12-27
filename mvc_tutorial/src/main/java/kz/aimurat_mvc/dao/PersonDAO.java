package kz.aimurat_mvc.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        // Jdbc Template
        // return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
        // //Custom mapper

        return jdbcTemplate.query("SELECT * FROM Person ORDER BY id ASC",
                new BeanPropertyRowMapper<>(Person.class));

        // OLD Jdbc
        // List<Person> people = new ArrayList<>();

        // try {
        // Statement statement = connection.createStatement();
        // String SQL = "SELECT * FROM person ORDER BY id ASC";
        // ResultSet resultSet = statement.executeQuery(SQL);

        // while (resultSet.next()) {
        // Person person = new Person();

        // person.setId(resultSet.getInt("id"));
        // person.setName(resultSet.getString("name"));
        // person.setSurname(resultSet.getString("surname"));
        // person.setAge(resultSet.getInt("age"));
        // person.setEmail(resultSet.getString("email"));

        // people.add(person);
        // }

        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        // return people;

    }

    public Person show(int i) {

        // Jdbc Template
        // Custom mapper
        // return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new
        // Object[]{i}, new PersonMapper())
        // .stream().findAny().orElse(null);

        // Built-in mapper
        return jdbcTemplate
                .query("SELECT * FROM person WHERE id = ?", new Object[] { i },
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

        // OLD Jdbc
        // Person person = new Person();

        // try {
        // PreparedStatement preparedStatement = connection.prepareStatement("SELECT *
        // FROM person WHERE id = ?");
        // preparedStatement.setInt(1, i);
        // ResultSet resultSet = preparedStatement.executeQuery();

        // while (resultSet.next()) {
        // person.setId(resultSet.getInt("id"));
        // person.setName(resultSet.getString("name"));
        // person.setSurname(resultSet.getString("surname"));
        // person.setAge(resultSet.getInt("age"));
        // person.setEmail(resultSet.getString("email"));

        // }

        // return person;
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        // People list
        // for (Person p : people) {
        // if (p.getId() == i) {
        // return p;
        // }
        // }
        // return null;

        // return null;
    }

    public void add(Person person) {

        // Jdbc template
        jdbcTemplate.update("INSERT INTO person(name, surname, age, email) VALUES(?, ?, ?, ?)",
                person.getName(), person.getSurname(),
                person.getAge(), person.getEmail());

        // OLD Jdbc
        // try {
        // PreparedStatement preparedStatement = connection
        // .prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?, ?)");

        // preparedStatement.setString(1, person.getName());
        // preparedStatement.setString(2, person.getSurname());
        // preparedStatement.setInt(3, person.getAge());
        // preparedStatement.setString(4, person.getEmail());

        // preparedStatement.executeUpdate();
        // } catch (SQLException e1) {
        // e1.printStackTrace();
        // }

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

        // Jdbc template
        jdbcTemplate.update("UPDATE person SET name = ?, surname = ?, age = ?, email = ? WHERE id = ?",
                person.getName(), person.getSurname(), person.getAge(), person.getEmail(), id);

        // Old Jdbc
        // try {
        // PreparedStatement preparedStatement = connection
        // .prepareStatement("UPDATE person SET name = ?, surname = ?, age = ?, email =
        // ? WHERE id = ?");

        // preparedStatement.setString(1, person.getName());
        // preparedStatement.setString(2, person.getSurname());
        // preparedStatement.setInt(3, person.getAge());
        // preparedStatement.setString(4, person.getEmail());

        // preparedStatement.setInt(5, id);

        // preparedStatement.executeUpdate();

        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        // Works with the List
        // Person oldPerson = this.show(id);
        // oldPerson.setName(person.getName());
        // oldPerson.setSurname(person.getSurname());
        // oldPerson.setEmail(person.getEmail());
        // oldPerson.setAge(person.getAge());

    }

    public void delete(int id) {

        // Jdbc template
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", new Object[] { id });

        // OLD Jdbc
        // try {
        // PreparedStatement preparedStatement = connection.prepareStatement("DELETE
        // FROM person WHERE id = ?");

        // preparedStatement.setInt(1, id);

        // preparedStatement.executeUpdate();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        // People list
        // this.people.remove(this.show(id));
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM person");

    }

    /////////////////////
    // Batch Update//
    /////////////////////

    public void testWithoutBatchUpdate() {
        List<Person> list = create1000Person();

        long before = System.currentTimeMillis();
        for (Person person : list) {
            jdbcTemplate.update("INSERT INTO person(name, surname, age, email) VALUES(?, ?, ?, ?)",
                    person.getName(), person.getSurname(),
                    person.getAge(), person.getEmail());
        }
        long after = System.currentTimeMillis();

        System.out.println("Time:  " + (after - before));

    }

    public void testWithBatchUpdate() {
        final List<Person> list = create1000Person();

        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO person(name, surname, age, email) VALUES(?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, list.get(i).getName());
                        ps.setString(2, list.get(i).getSurname());
                        ps.setInt(3, list.get(i).getAge());
                        ps.setString(4, list.get(i).getEmail());

                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });

        long after = System.currentTimeMillis();
        System.out.println("Time: Batch " + (after - before));

    }

    private List<Person> create1000Person() {
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(new Person(i, "Name" + i, "Surname" + i, "test" + i + "@gmail.com", 20));
        }

        return list;
    }
}
