package com.andrii;

import com.andrii.app.WicketApplication;
import com.andrii.app.page.login.LoginPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestLoginPage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(LoginPage.class);

		//assert rendered page class
		tester.assertRenderedPage(LoginPage.class);
	}
}
