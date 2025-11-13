package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.Utilities;
import ru.stqa.mantis.model.IssueData;

public class IssueCreationTests extends TestBase {
    @Test
    public void testIssueCreation() throws Exception {
        app.soapApi().createIssue(new IssueData().withSummary(Utilities.stringGenerator(10))
        .withDescription(Utilities.stringGenerator(50))
                .withProject(1L));
    }
}
