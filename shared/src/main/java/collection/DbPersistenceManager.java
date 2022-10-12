package collection;

import communication.User;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbPersistenceManager implements PersistanceManager {
    public static final DbPersistenceManager INSTANCE = new DbPersistenceManager();
    private final DbConnector connector;

    private DbPersistenceManager() {
        this.connector = new DbConnector();
    }

    @Override
    public Optional<User> getUser(String login) {
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            var ps = connector.getStatement(sql);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String user_login = resultSet.getString("username");
                String password = resultSet.getString("password");
                long id = resultSet.getInt("id");
                return Optional.of(new User(user_login, password, id));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.err.println("Failed to get user: " + e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> addUser(User user) {
        var sql = "INSERT INTO users VALUES (DEFAULT, ?, ?)";
        try {
            var ps = connector.getStatement(sql);
            ps.setString(1, user.username());
            ps.setString(2, user.password());
            if (ps.executeUpdate() != 0) {
                return Optional.of(DbConnector.getInsertId(ps));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<MusicBand> getBands() {
        String sql = "SELECT * FROM \"music_bands\"";
        List<MusicBand> bands = new ArrayList<>();
        try {
            var ps = connector.getStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                bands.add(buildBand(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("SQLException getting bands" + e);
        }
        return bands;
    }

    @Override
    public boolean removeBandById(long id) {
        var sql = "DELETE FROM music_bands WHERE id=?";
        try {
            PreparedStatement statement = connector.getStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Marine with id " + id + "  deleted successfully");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Long> addBand(MusicBand musicBand) {
        var sql = "INSERT INTO music_bands VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connector.getStatement(sql);
            statement.setString(1, musicBand.name);
            statement.setDouble(2, musicBand.coordinates.x);
            statement.setInt(3, musicBand.coordinates.y);
            statement.setDate(4, java.sql.Date.valueOf(musicBand.creationDate));
            statement.setLong(5, musicBand.numberOfParticipants);
            statement.setString(6, musicBand.genre.name());
            statement.setString(7, musicBand.name);

            statement.setDouble(8, musicBand.frontMan.height);
            statement.setLong(9, musicBand.frontMan.weight);
            statement.setString(10, musicBand.frontMan.eyeColor.name());
            statement.setFloat(11, musicBand.frontMan.location.x);
            statement.setFloat(12, musicBand.frontMan.location.y);
            statement.setString(13, musicBand.frontMan.location.name);

            if (statement.executeUpdate() != 0) {
                System.out.println("Band added to db!");
            } else {
                System.out.println("Failed to add band to db");
                return Optional.empty();
            }
            return Optional.of(DbConnector.getInsertId(statement));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private MusicBand buildBand(ResultSet rs) throws SQLException {
        MusicBand res = new MusicBand();
        res.id = rs.getLong("id");
        res.name = rs.getString("name");

        var coordinates = new Coordinates();
        coordinates.x = rs.getDouble("coordinates_x");
        coordinates.y = rs.getInt("coordinates_y");
        res.coordinates = coordinates;
        res.creationDate = rs.getDate("creation_date").toLocalDate();

        res.numberOfParticipants = rs.getLong("number_of_participants");
        res.genre = MusicGenre.valueOf(rs.getString("genre").toUpperCase());
        var person = new Person();
        person.name = rs.getString("person_name");
        person.height = rs.getDouble("person_height");
        person.weight = rs.getLong("person_weight");
        person.eyeColor = Color.valueOf(rs.getString("person_eyecolor").toUpperCase());
        var location = new Location();
        location.x = rs.getFloat("person_location_x");
        location.y = rs.getFloat("person_location_y");
        location.name = rs.getString("person_location_name");
        person.location = location;
        res.frontMan = person;
        return res;
    }
}