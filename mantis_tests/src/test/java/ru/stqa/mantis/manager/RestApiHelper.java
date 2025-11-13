package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.stqa.mantis.model.Credentials;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

public class RestApiHelper extends HelperBase{
    public RestApiHelper(AppManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("api.key"));
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
        IssuesApi apiInstance = new IssuesApi();// Issue | The issue to add.
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new  RuntimeException(e);
        }
    }
    public void userAdd( UserData userData) {
        UserApi apiInstance = new UserApi();
        User user = new User();
        user.setUsername(userData.username());
        user.setPassword(userData.password());
        user.setRealName(userData.realName());
        user.setEmail(userData.email());
        try {
            UserAddResponse result = apiInstance.userAdd(user);
            System.out.println(result);
        } catch (ApiException e) {
            throw new  RuntimeException(e);
        }
    }
}
