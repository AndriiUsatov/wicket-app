package com.andrii.app.form;

import com.andrii.app.domain.User;
import com.andrii.app.page.user.UserListPage;
import com.andrii.app.service.UserService;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;

public class AddUserForm extends Form {

    private static UserService userService = UserService.getUserServiceInstance();

    public AddUserForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new TextField("loginSetField", Model.of("")));
        add(new TextField("firstNameSetField", Model.of("")));
        add(new TextField("lastNameSetField", Model.of("")));
        add(new TextField("middleNameSetField", Model.of("")));
        add(new PasswordTextField("passwordSetField", Model.of("")));
        add(new TextField("mailSetField", Model.of("")));
        add(new SubmitLink("createUser", Model.of("")));
        add(new Button("toList", Model.of("")) {
            @Override
            public void onSubmit() {
                setResponsePage(UserListPage.class);
            }
        }.setDefaultFormProcessing(false));
    }

    @Override
    protected void onSubmit() {
        String login = get("loginSetField").getDefaultModelObjectAsString();
        String password = get("passwordSetField").getDefaultModelObjectAsString();
        String firstName = get("firstNameSetField").getDefaultModelObjectAsString();
        String lastName = get("lastNameSetField").getDefaultModelObjectAsString();
        String middleName = get("middleNameSetField").getDefaultModelObjectAsString();
        String mail = get("mailSetField").getDefaultModelObjectAsString();
        userService.addUser(User.builder()
                .login(login)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .mail(mail)
                .build());
        setResponsePage(UserListPage.class);
    }
}
