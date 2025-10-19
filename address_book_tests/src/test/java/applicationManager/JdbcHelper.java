package applicationManager;

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
        try (var connection = DriverManager.getConnection(manager.properties.getProperty("db.url"), manager.properties.getProperty("db.user"), "");
             var statement = connection.createStatement();
             var result = statement.executeQuery(manager.properties.getProperty("db.requestGroups"))) {

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
}
