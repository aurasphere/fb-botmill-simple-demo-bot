/*
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package co.aurasphere.botmill.demo.fb;

import co.aurasphere.botmill.core.internal.util.ConfigurationUtils;
import co.aurasphere.botmill.fb.FbBotMillContext;
import co.aurasphere.botmill.fb.messengerprofile.FbBotMillMessengerProfileConfiguration;

/**
 * Class which handles the bot Messenger Profile Configuration. This class has a
 * main since it can be called any time and it's not bound to the application.
 * 
 * @author Donato Rimenti
 */
public class MessengerProfileConfiguration {

	/**
	 * Keys of the property constants.
	 */
	private static final String FB_BOTMILL_PAGE_TOKEN = "fb.page.token";
	private static final String FB_BOTMILL_VALIDATION_TOKEN = "fb.validation.token";

	/**
	 * Sets up the Get Started Button and the Greeting Message.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ConfigurationUtils.loadConfigurationFile();
		FbBotMillContext.getInstance().setup(
				ConfigurationUtils.getConfiguration().getProperty(
						FB_BOTMILL_PAGE_TOKEN),
				ConfigurationUtils.getConfiguration().getProperty(
						FB_BOTMILL_VALIDATION_TOKEN));

		FbBotMillMessengerProfileConfiguration
				.setGetStartedButton("get_started");
		FbBotMillMessengerProfileConfiguration
				.setGreetingMessage("Hello, I'm a simple BotMill-based bot!");
	}

}
