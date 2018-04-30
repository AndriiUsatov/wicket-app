package com.andrii.app.form;

import com.andrii.app.domain.User;
import com.andrii.app.page.user.UserListPage;
import com.andrii.app.service.UserService;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class EditUserForm extends Form {

    private static UserService userService = UserService.getUserServiceInstance();
    private User user;

    public EditUserForm(String id, User user) {
        super(id);
        this.user = user;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new TextField("loginEdit", new PropertyModel(user, "login")));
        add(new TextField("passwordEdit", new PropertyModel(user, "password")));
        add(new TextField("firstNameEdit", new PropertyModel(user, "firstName")));
        add(new TextField("lastNameEdit", new PropertyModel(user, "lastName")));
        add(new TextField("middleNameEdit", new PropertyModel(user, "middleName")));
        add(new TextField("mailEdit", new PropertyModel(user, "mail")));
        add(new SubmitLink("updateUser", Model.of("")));
        add(new Button("toList", Model.of("")){
            @Override
            public void onSubmit() {
                setResponsePage(UserListPage.class);
            }
        }.setDefaultFormProcessing(false));
    }

    @Override
    protected void onSubmit() {
        userService.updateUser(User.builder()
        .id(user.getId())
        .login(get("loginEdit").getDefaultModelObjectAsString())
        .password(get("passwordEdit").getDefaultModelObjectAsString())
        .firstName(get("firstNameEdit").getDefaultModelObjectAsString())
        .lastName(get("lastNameEdit").getDefaultModelObjectAsString())
        .middleName(get("middleNameEdit").getDefaultModelObjectAsString())
        .mail(get("mailEdit").getDefaultModelObjectAsString())
        .build());
        setResponsePage(UserListPage.class);
    }

}
