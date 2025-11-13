package ru.stqa.mantis.manager;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import io.swagger.client.ApiException;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapApiHelper extends HelperBase{
    MantisConnectPortType mantis;
    public SoapApiHelper(AppManager manager) {
        super(manager);
        try {
            mantis = new MantisConnectLocator().getMantisConnectPort(new URL(manager.property("soap.endpoint")));
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createIssue(IssueData issueData) {
        try {
            var categories = mantis.mc_project_get_categories(manager.property("web.username"), manager.property("web.pwd"), BigInteger.valueOf(issueData.project()));
            var issue =  new  biz.futureware.mantis.rpc.soap.client.IssueData();
            issue.setSummary(issueData.summary());
            issue.setDescription(issueData.description());
            issue.setCategory(categories[0]);
            var projectId = new ObjectRef();
            projectId.setId(BigInteger.valueOf(issueData.project()));
            issue.setProject(projectId);
            mantis.mc_issue_add(manager.property("web.username"), manager.property("web.pwd"), issue );
        } catch (RemoteException e) {
            throw new RuntimeException(e);
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
