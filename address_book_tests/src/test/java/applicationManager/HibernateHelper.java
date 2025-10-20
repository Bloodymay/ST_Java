package applicationManager;
import applicationManager.hbn.GroupRecord;
import model.Contact;
import model.Group;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;
    public HibernateHelper(AppManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        //.addAnnotatedClass(Group.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .setProperty(AvailableSettings.URL,manager.properties.getProperty("db.url"))
                        .setProperty(AvailableSettings.USER,manager.properties.getProperty("db.username"))
                        .setProperty(AvailableSettings.PASS,manager.properties.getProperty("db.pwd"))
                        .buildSessionFactory();
    }
    static List<Group> convertList(List<GroupRecord> records) {
        List<Group> result  = new ArrayList<>();
        for (GroupRecord record : records) {
            result.add(convert(record));
        }
        return result;

    }

    private static Group convert(GroupRecord record) {
        return new Group("" + record.id, record.name, record.header, record.footer);
    }

    public List<Group> getGroupsListHnt() {
        return convertList(sessionFactory.fromSession(session -> { return  session.createQuery("from GroupRecord ", GroupRecord.class).list();}));

    }

}
