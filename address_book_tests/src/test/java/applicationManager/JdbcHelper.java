package applicationManager;

import model.Contact;
import model.Group;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(AppManager manager) {
        super(manager);
    }

    public List<Group> getGroupList() {
        var groups = new ArrayList<Group>();
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.user"), manager.properties.getProperty("db.pwd"));
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
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.user"), manager.properties.getProperty("db.pwd"));
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
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.user"), manager.properties.getProperty("db.pwd"));
             var statement = connection.createStatement();
             var result = statement.executeQuery("select * from address_in_groups ag LEFT JOIN addressbook ab ON ab.id=ag.id WHERE ab.id IS NULL")) {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkContactInGroup(Group group) throws SQLException {
        var group_id = Integer.parseInt(group.id());
        String sql = "SELECT COUNT(*) FROM address_in_groups WHERE group_id = ?";

        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.user"), manager.properties.getProperty("db.pwd"));
             var statement = connection.prepareStatement(sql)) {

            statement.setInt(1, group_id);
            var result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1) > 0; // если count > 0, значит записи есть
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
