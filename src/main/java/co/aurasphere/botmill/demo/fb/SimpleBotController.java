/*
 * MIT License
 *
 * Copyright (c) 2016-2017 BotMill.io
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

import co.aurasphere.botmill.core.annotation.Bot;
import co.aurasphere.botmill.demo.fb.reply.ConversationHandlerAutoReply;
import co.aurasphere.botmill.demo.fb.reply.StartWritingAutoReply;
import co.aurasphere.botmill.fb.FbBot;
import co.aurasphere.botmill.fb.event.FbBotMillEventType;
import co.aurasphere.botmill.fb.model.annotation.FbBotMillController;

/**
 * Class that represents a bot and handles the user interaction.
 * 
 * @author Donato Rimenti
 */
@Bot
public class SimpleBotController extends FbBot {

	/**
	 * Called whenever an user sends any message to this bot. Starts the
	 * conversation.
	 */
	@FbBotMillController(eventType = FbBotMillEventType.MESSAGE_PATTERN, pattern = ".+")
	public void onAnyMessageReceived() {
		reply(new StartWritingAutoReply(), new ConversationHandlerAutoReply());
	}

	/**
	 * Called when the get started button is pressed. Starts the conversation.
	 */
	@FbBotMillController(eventType = FbBotMillEventType.POSTBACK, postback = "get_started")
	public void onGetStartedButtonPressed() {
		reply(new StartWritingAutoReply(), new ConversationHandlerAutoReply());
	}

}
