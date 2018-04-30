package com.andrii.app.form;

import com.andrii.app.domain.User;
import com.andrii.app.page.user.UserListPage;
import com.andrii.app.service.UserService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class SearchUserForm extends Form {

    private static UserService userService = UserService.getUserServiceInstance();
    private List<User> foundUsers;

    public SearchUserForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new TextField("searchUserFirstName", Model.of("")));
        add(new TextField("searchUserLastName", Model.of("")));
        add(new TextField("searchUserMiddleName", Model.of("")));
    }

    @Override
    protected void onSubmit() {
        PageParameters parameters = new PageParameters();
        String firstName = get("searchUserFirstName").getDefaultModelObjectAsString();
        String lastName = get("searchUserLastName").getDefaultModelObjectAsString();
        String middleName = get("searchUserMiddleName").getDefaultModelObjectAsString();
        if (firstName.equals("") && lastName.equals("") && middleName.equals(""))
            setResponsePage(UserListPage.class);
        else {
            parameters.add("firstNameFilter", firstName);
            parameters.add("lastNameFilter", lastName);
            parameters.add("middleNameFilter", middleName);
            setResponsePage(UserListPage.class, parameters);
        }
    }

    public List<User> getFoundUsers() {
        return foundUsers;
    }
}

