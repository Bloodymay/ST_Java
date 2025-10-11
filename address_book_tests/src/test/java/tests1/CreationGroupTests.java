package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreationGroupTests extends TestBase {

    public static List<Group> groupProvider() {
        var result = new ArrayList<Group>(List.of(new Group("", "name", "footr", "header"),
                new Group("", "name", "barr", "header")
        ));
        for (var name : List.of("", "group_name")) {
            for (var header : List.of("", "header_name")) {
                for (var footer : List.of("", "footer_name")) {
                    result.add(new Group().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new Group()
                    .withName(stringGenerator(i))
                    .withHeader(stringGenerator(i))
                    .withHeader(stringGenerator(i)));
        }
        return result;

    }

    public static List<Group> negativeGroupProvider() {
        var result = new ArrayList<Group>(List.of(new Group("", "name'", "footr", "header")
        ));
        return result;


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
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(Group group) throws InterruptedException {

        var oldGroups = app.getGroups().getList();
        app.getGroups().creatingGroup(group);
        var newGroups = app.getGroups().getList();
        app.getGroups().openPage();
        Assertions.assertEquals(newGroups, oldGroups);

    }

}
