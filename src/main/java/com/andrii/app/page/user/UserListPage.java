package com.andrii.app.page.user;

import com.andrii.app.domain.User;
import com.andrii.app.page.AuthenticatedWebPage;
import com.andrii.app.page.home.HomePage;
import com.andrii.app.service.UserService;
import com.andrii.app.util.SignInSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.util.List;
import java.util.stream.Collectors;

public class UserListPage extends WebPage implements AuthenticatedWebPage {

    private static UserService userService = UserService.getUserServiceInstance();

    @Override
    public void onInitialize(){
        super.onInitialize();
        add(new Label("helloMessage", "User list"));

        List<User> list = userService.getAllUsers().stream()
                .filter(u -> !u.equals(((SignInSession)getSession()).getUser()))
                .collect(Collectors.toList());
        add(new Link("addUser") {
            @Override
            public void onClick() {
                setResponsePage(AddUserPage.class);
            }
        });

        ListView<User> listView = new ListView<User>("listUser", list) {
            @Override
            protected void populateItem(ListItem<User> listItem) {
                listItem.add(new Label("login", listItem.getModel().getObject().getLogin()));
//                listItem.add(new Label("password", listItem.getModel().getObject().getPassword()));
                listItem.add(new Label("firstName", listItem.getModel().getObject().getFirstName()));
                listItem.add(new Label("lastName", listItem.getModel().getObject().getLastName()));
                listItem.add(new Label("middleName", listItem.getModel().getObject().getMiddleName()));
                listItem.add(new Label("mail", listItem.getModel().getObject().getMail()));
                listItem.add(new Link("editButton", listItem.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(EditUserPage.class);
                    }
                });
                listItem.add(new Link("removeButton", listItem.getModel()) {
                    @Override
                    public void onClick() {
                        userService.removeUser((User)getModel().getObject());
                        setResponsePage(UserListPage.class);
                    }
                });
            }
        };
        add(listView);

        add(new Link("logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(HomePage.class);
            }
        });

    }
}
