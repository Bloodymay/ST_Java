package applicationManager;
import applicationManager.hbn.GroupRecord;
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

    private static GroupRecord convert2(Group group) {
        var id = group.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), group.name(), group.header(), group.footer());
    }


    public List<Group> getGroupsListHnt() {
        return convertList(sessionFactory.fromSession(session -> { return  session.createQuery("from GroupRecord ", GroupRecord.class).list();}));

    }

    public long getGroupCount() {
        return (sessionFactory.fromSession(session -> { return  session.createQuery("select count (*) from GroupRecord ", Long.class).getSingleResult();}));
    }

    public void creatingGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert2(group));
            session.getTransaction().commit();
        });

    }
}
