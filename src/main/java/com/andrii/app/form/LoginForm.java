package com.andrii.app.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginForm extends Form{


    public LoginForm(String id){
        super(id);
        init();
    }

    private void init(){
        setDefaultModel(new CompoundPropertyModel<Object>(this));
        add(new TextField("login"));
        add(new PasswordTextField("password"));
    }

    @Override
    public void onSubmit(){

    }
}
