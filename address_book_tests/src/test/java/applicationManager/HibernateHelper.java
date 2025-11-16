package applicationManager;

import applicationManager.hbn.ContactRecord;
import applicationManager.hbn.GroupRecord;
import io.qameta.allure.Step;
import model.Contact;
import model.Group;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;


public class HibernateHelper extends HelperBase {
    private final SessionFactory sessionFactory;

    public HibernateHelper(AppManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        //.addAnnotatedClass(ContactGroupRecord.class)
                        .setProperty(AvailableSettings.URL, manager.properties.getProperty("db.url"))
                        .setProperty(AvailableSettings.USER, manager.properties.getProperty("db.username"))
                        .setProperty(AvailableSettings.PASS, manager.properties.getProperty("db.pwd"))
                        .buildSessionFactory();
    }

    static List<Contact> convertContactList(List<ContactRecord> records) {
//        List<Contact> result = new ArrayList<>(); через цикл
//        for (ContactRecord record : records) {
//            result.add(convertRecordToContact(record));
//        }
//        return result;
//
        return records.stream().map(HibernateHelper::convertRecordToContact).collect(Collectors.toList()); //через трансформатор
    }

    private static Contact convertRecordToContact(ContactRecord record) {
        return new Contact()
                .mainFieldsWithID("" + record.id, record.firstname, record.lastname, record.address, record.mobile)
                .withHomePhone(record.home)
                .withWorkPhone(record.work)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3);
    }

    private static ContactRecord convertContactToRecord(Contact contact) {
        var id = contact.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), contact.firstName(), contact.lastName(), contact.address(), contact.mobilePhone(), contact.email(), contact.email2());
    }

    static List<Group> convertGroupList(List<GroupRecord> records) {
//        List<Group> result = new ArrayList<>(); через цикл
//        for (GroupRecord record : records) {
//            result.add(convertRecordToGroup(record));
//        }
//        return result;
        return records.stream().map(HibernateHelper::convertRecordToGroup).collect(Collectors.toList()); //через трансформатор

    }

    private static Group convertRecordToGroup(GroupRecord record) {
        return new Group("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convertGroupToRecord(Group group) {
        var id = group.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), group.name().trim(), group.header().trim(), group.footer().trim());
    }
    @Step
    public void creatingContact(Contact contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContactToRecord(contact));
            session.getTransaction().commit();
        });
    }

    public int creatingContactAndReturnID(Contact contact) {
        final int[] result = new int[1];
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            ContactRecord record = convertContactToRecord(contact);
            session.persist(record);
            session.getTransaction().commit();
            result[0] = record.getID();
        });
        return result[0];
    }
    @Step
    public List<Contact> getContactsListHnt() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));

    }


    public List<Group> getGroupsListHnt() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord ", GroupRecord.class).list();
        }));

    }


    public long getGroupCount() {
        return (sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord ", Long.class).getSingleResult();
        }));
    }

    public void creatingGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroupToRecord(group));
            session.getTransaction().commit();
        });

    }

    public Contact getContactById(int id) {
        return convertRecordToContact(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord where id = :id", ContactRecord.class).setParameter("id", id).getSingleResult();
        }));
    }

    public List<Contact> getContactsInGroup(Group group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }


    public int contactCounter() {
        return getContactsListHnt().size();
    }
}
