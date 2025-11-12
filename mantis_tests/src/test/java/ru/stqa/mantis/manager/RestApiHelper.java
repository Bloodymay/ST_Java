package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import ru.stqa.mantis.model.IssueData;

public class RestApiHelper extends HelperBase{
    public RestApiHelper(AppManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var categoryIdentifier = new Identifier();
        categoryIdentifier.setId(issueData.category());
        issue.setCategory(categoryIdentifier);
        var projectIdentifier = new Identifier();
        projectIdentifier.setId(issueData.project());
        issue.setProject(projectIdentifier);

        IssuesApi apiInstance = new IssuesApi();
        Issue body = new Issue(); // Issue | The issue to add.
        try {
            apiInstance.issueAdd(body);
        } catch (ApiException e) {
            new  RuntimeException(e);
        }
    }
}
