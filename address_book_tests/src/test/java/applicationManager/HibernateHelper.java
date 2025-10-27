package applicationManager;
import applicationManager.hbn.ContactRecord;
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
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        //.addAnnotatedClass(ContactGroupRecord.class)
                        .setProperty(AvailableSettings.URL,manager.properties.getProperty("db.url"))
                        .setProperty(AvailableSettings.USER,manager.properties.getProperty("db.username"))
                        .setProperty(AvailableSettings.PASS,manager.properties.getProperty("db.pwd"))
                        .buildSessionFactory();
    }
    static List<Contact> convertContactList(List<ContactRecord> records) {
        List<Contact> result  = new ArrayList<>();
        for (ContactRecord record : records) {
            result.add(convertRecordToContact(record));
        }
        return result;

    }

    private static Contact convertRecordToContact(ContactRecord record) {
        return new Contact().mainFieldsWithID("" + record.id, record.firstname, record.lastname, record.address, record.mobile);
    }

    private static ContactRecord convertContactToRecord(Contact contact) {
        var id = contact.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), contact.firstName(), contact.lastName(), contact.address(), contact.mobilePhone());
    }
    public void creatingContact(Contact contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContactToRecord(contact));
            session.getTransaction().commit();
        });
    }
    static List<Group> convertList(List<GroupRecord> records) {
        List<Group> result  = new ArrayList<>();
        for (GroupRecord record : records) {
            result.add(convertRecordToGroup(record));
        }
        return result;

    }

    private static Group convertRecordToGroup(GroupRecord record) {
        return new Group("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convertGroupToRecord(Group group) {
        var id = group.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), group.name(), group.header(), group.footer());
    }
    public List<Contact> getContactsListHnt() {
        return convertContactList(sessionFactory.fromSession(session -> { return  session.createQuery("from ContactRecord", ContactRecord.class).list();}));

    }

    public long getContactCount() {
        return (sessionFactory.fromSession(session -> { return  session.createQuery("select count (*) from ContactRecord ", Long.class).getSingleResult();}));
    }

    public List<Group> getGroupsListHnt() {
        return convertList(sessionFactory.fromSession(session -> { return  session.createQuery("from GroupRecord ", GroupRecord.class).list();}));

    }
//    public List<ContactGroupRecord> getRelationsListHnt() {
//        return (sessionFactory.fromSession(session -> { return  session.createQuery("from ContactGroupRecord", ContactGroupRecord.class).list();}));
//    }



    public long getGroupCount() {
        return (sessionFactory.fromSession(session -> { return  session.createQuery("select count (*) from GroupRecord ", Long.class).getSingleResult();}));
    }

    public void creatingGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroupToRecord(group));
            session.getTransaction().commit();
        });

    }

    public List<Contact> getContactsInGroup(Group group) {
        return  sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }


    public int contactCounter() {
        return getContactsListHnt().size();
    }
}
