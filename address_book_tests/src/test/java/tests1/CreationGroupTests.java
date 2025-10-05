package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreationGroupTests extends TestBase {

    public static List<Group> groupProvider() {
        var result = new ArrayList<Group>(List.of(new Group("name", "footr", "header"),
                new Group("name", "barr", "header")
        ));
        for (var name : List.of("", "group_name")) {
            for (var header : List.of("", "header_name")) {
                for (var footer : List.of("", "footer_name")) {
                    result.add(new Group(name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new Group(stringGenerator(i), stringGenerator(i), stringGenerator(i)));
        }
        return result;

    }

    public static List<Group> negativeGroupProvider() {
        var result = new ArrayList<Group>(List.of(new Group("name'", "footr", "header")
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

        int groupCount = app.getGroups().getCount();
        app.getGroups().creatingGroup(group);

        int newGroupCount = app.getGroups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateMultipleGroups(Group group) {

        int groupCount = app.getGroups().getCount();
        app.getGroups().creatingGroup(group);
        app.getGroups().openGroupPage();
        int newGroupCount = app.getGroups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);

    }

}
