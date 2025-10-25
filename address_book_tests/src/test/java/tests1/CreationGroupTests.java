package tests1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.Utilities;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreationGroupTests extends TestBase {

    public static List<Group> groupProvider() throws IOException {
        var result = new ArrayList<Group>();//(List.of(new Group("", "name", "footr", "header"),
        // new Group("", "name", "barr", "header")
        //));
//        for (var name : List.of("", "group_name")) {
//            for (var header : List.of("", "header_name")) {
//                for (var footer : List.of("", "footer_name")) {
//                    result.add(new Group().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }
//          1й метод чтения из файла
//        ObjectMapper mapper = new ObjectMapper();
//        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<Group>>() {});
//        result.addAll(value);

        //2й метод чтения из файла
        var xml = Files.readString(Paths.get("groups.xml"));

        var mapper = new XmlMapper();
        var value = mapper.readValue(xml, new TypeReference<List<Group>>() {
        });
        result.addAll(value);

        //3й метод чтения из файла
//        var json = "";
//       try(var reader=new FileReader("groups.json");
//           var breader = new BufferedReader(reader)){
//           var line = breader.readLine();
//           while(line!=null){
//               json += line;
//               line = breader.readLine();
//           }
//
//       }
//        ObjectMapper mapper = new ObjectMapper();
//        var value = mapper.readValue(json, new TypeReference<List<Group>>() {});
//        result.addAll(value);


        return result;

    }

    public static List<Group> negativeGroupProvider() {
        var result = new ArrayList<Group>(List.of(new Group("", "name'", "footr", "header")
        ));
        return result;


    }
    public static List<Group> singleGroupProvider() {
        return  List.of(new Group()
                .withName(Utilities.stringGenerator(10))
                .withHeader(Utilities.stringGenerator(20))
                .withFooter(Utilities.stringGenerator(30)));

    }


//    @Test
//    public void canCreateGroupWithNameOnly() {
//        var emptyGroup = new Group();
//        var groupWithName = new Group().withName("new_name");
//        app.getGroups().creatingGroup(groupWithName);
//    }
//
//    @Test
//    public void canCreateGroupWithHeaderOnly() {
//        app.getGroups().creatingGroup(new Group().withHeader("SomeHeader"));
//    }
//
//    @Test
//    public void canCreateGroupWithFooterOnly() {
//        app.getGroups().creatingGroup(new Group().withFooter("SomeFooter"));
//    }
//
//    @Test
//    public void canCreateEmptyGroup() {
//        app.getGroups().creatingGroup(new Group());
    //   }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(Group group) {
        var oldGroups = app.getGroups().getList();
        app.getGroups().creatingGroup(group);
        var newGroups = app.getGroups().getList();

        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareByID);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withID(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareByID);
        Assertions.assertEquals(newGroups, expectedList);

    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroupsHbn(Group group) { //тест на создание нескольких групп посредством Hibernate
        var oldGroups = app.getHibernate().getGroupsListHnt();
        app.getHibernate().creatingGroup(group);
        var newGroups = app.getHibernate().getGroupsListHnt();

        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareByID);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withID(newGroups.get(newGroups.size() - 1).id()));
        expectedList.sort(compareByID);
        Assertions.assertEquals(newGroups, expectedList);

    }

    @ParameterizedTest
    @MethodSource("singleGroupProvider")
    public void canCreateSingleGroupFromDB(Group group) {
        var oldGroups = app.getJdbc().getGroupList();
        app.getGroups().creatingGroup(group);
        var newGroups = app.getJdbc().getGroupList();

        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareByID);
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withID(maxID));
        expectedList.sort(compareByID);
        //var newUIgroups = app.getGroups().getList();
        //var newDBGroups = app.getJdbc().getGroupListWIthIdAndName();
        //newUIgroups.sort(compareByID);
        //newDBGroups.sort(compareByID);
        Assertions.assertEquals(newGroups, expectedList);
        //Assertions.assertEquals(newUIgroups, newDBGroups);

    }

    @ParameterizedTest
    @MethodSource("singleGroupProvider")
    public void canCreateSingleGroupWithHbn(Group group) {
        var oldGroups = app.getHibernate().getGroupsListHnt();
        app.getGroups().creatingGroup(group);
        var newGroups = app.getHibernate().getGroupsListHnt();

        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareByID);
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withID(maxID));
        expectedList.sort(compareByID);
        var newUIgroups = app.getGroups().getList();
        var newDBGroups = app.getJdbc().getGroupListWIthIdAndName();
        newUIgroups.sort(compareByID);
        newDBGroups.sort(compareByID);
        Assertions.assertEquals(newGroups, expectedList);
        Assertions.assertEquals(newUIgroups, newDBGroups);

    }

    @Test
    public void groupMatchingCheck() { //Проверка соответствия визуального представления списку групп в БД

        var newUIgroups = app.getGroups().getList();
        var newDBGroups = app.getJdbc().getGroupListWIthIdAndName();
        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUIgroups.sort(compareByID);
        newDBGroups.sort(compareByID);
        Assertions.assertEquals(newUIgroups, newDBGroups);

    }


    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(Group group) throws InterruptedException {

        var oldGroups = app.getGroups().getList();
        app.getGroups().creatingGroup(group);
        var newGroups = app.getGroups().getList();
        app.getGroups().openPage();
        Assertions.assertEquals(newGroups, oldGroups);

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider") // тест на несоздание группы посредством Hibernate
    public void canNotCreateGroupHbn(Group group) throws InterruptedException {

        var oldGroups = app.getHibernate().getGroupsListHnt();
        app.getGroups().creatingGroup(group);//использован метод из groupHelper т.к. напрямую в базу записывается название группы через апостроф и тест становится бесполезным
        var newGroups = app.getHibernate().getGroupsListHnt();
        app.getGroups().openPage();
        Assertions.assertEquals(newGroups, oldGroups);

    }


}
