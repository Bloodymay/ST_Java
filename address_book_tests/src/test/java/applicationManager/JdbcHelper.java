package applicationManager;

import model.Contact;
import model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(AppManager manager) {
        super(manager);
    }

    public List<Group> getGroupList() {
        var groups = new ArrayList<Group>();
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.username"), manager.properties.getProperty("db.pwd"));
             var statement = connection.createStatement();
             var result = statement.executeQuery("select group_id,group_name, group_header,group_footer from group_list")) {

            while (result.next()) {
                groups.add(new Group()
                        .withID(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return groups;
    }

    public List<Group> getGroupListWIthIdAndName() {
        var groups = new ArrayList<Group>();
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.username"), manager.properties.getProperty("db.pwd"));
             var statement = connection.createStatement();
             var result = statement.executeQuery("select group_id,group_name from group_list")) {

            while (result.next()) {
                groups.add(new Group()
                        .withID(result.getString("group_id"))
                        .withName(result.getString("group_name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return groups;
    }

    public void checkConsistensy() throws SQLException {
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.username"), manager.properties.getProperty("db.pwd"));
             var statement = connection.createStatement();
             var result = statement.executeQuery("select * from address_in_groups ag LEFT JOIN addressbook ab ON ab.id=ag.id WHERE ab.id IS NULL")) {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkContactsInGroup(Group group) throws SQLException {
        var group_id = Integer.parseInt(group.id());
        String sql = "SELECT COUNT(*) FROM address_in_groups WHERE group_id = ?";

        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.username"), manager.properties.getProperty("db.pwd"));
             var statement = connection.prepareStatement(sql)) {

            statement.setInt(1, group_id);
            var result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1) > 0; // если count > 0, значит записи есть
            }
            return false;
        }
    }

    public boolean checkCertainContactInGroup(Group group, Contact contact) throws SQLException {
        var idList = getContactIdList(group);
        if (idList.size() == 0) {
            return true;
        }
        if (idList.contains(Integer.parseInt(contact.id()))) {
            return true;
        } else return false;
    }

    public List<Integer> getContactIdList(Group group) throws SQLException {
        int group_id = Integer.parseInt(group.id());
        String sql = "SELECT id FROM address_in_groups WHERE group_id = ?";

        List<Integer> idList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                manager.properties.getProperty("db.url"),
                manager.properties.getProperty("db.username"),
                manager.properties.getProperty("db.pwd"));
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, group_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    idList.add(id);
                }
            }
        }
        return idList;
    }
}
