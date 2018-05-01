package com.andrii.app.util;

import org.apache.wicket.request.IExceptionMapper;
import org.apache.wicket.util.IProvider;

import java.security.Provider;

public class AppIProvider implements IProvider{

    private static IExceptionMapper mapper = new AppExceptionMapper();
    private static volatile IProvider providerInstance;

    private AppIProvider(){}

    public static IProvider getProviderInstance(){
        if(providerInstance == null){
            synchronized (Provider.class){
                if(providerInstance == null){
                    providerInstance = new AppIProvider();
                }
            }
        }
        return providerInstance;
    }


    @Override
    public IExceptionMapper get() {
        return mapper;
    }
}
