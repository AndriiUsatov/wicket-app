package com.andrii.app.util;

import com.andrii.app.page.error.ErrorPage;
import org.apache.wicket.DefaultExceptionMapper;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.IRequestHandler;

public class AppExceptionMapper extends DefaultExceptionMapper {
    @Override
    public IRequestHandler map(Exception e) {
        return new RenderPageRequestHandler(new PageProvider(ErrorPage.class));
    }

}
