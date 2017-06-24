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
package co.aurasphere.botmill.demo.fb.reply;

import co.aurasphere.botmill.core.BotMillSession;
import co.aurasphere.botmill.core.datastore.model.KeyValuePair;
import co.aurasphere.botmill.fb.autoreply.AutoReply;
import co.aurasphere.botmill.fb.model.incoming.MessageEnvelope;
import co.aurasphere.botmill.fb.model.outcoming.FbBotMillResponse;
import co.aurasphere.botmill.fb.model.outcoming.factory.ReplyFactory;

/**
 * {@link AutoReply} which handles a specific conversation flow step by step
 * using the {@link BotMillSession}.
 * 
 * @author Donato Rimenti
 */
public class ConversationHandlerAutoReply extends AutoReply {

	/**
	 * The session manager object.
	 */
	private final static BotMillSession SESSION_MANAGER = BotMillSession
			.getInstance();

	/**
	 * The key of the current conversation step into the session.
	 */
	private final static String CURRENT_CONVERSATION_STEP_SESSION_KEY = "conversationStep";

	/**
	 * The key of the email object into the session.
	 */
	private final static String EMAIL_SESSION_KEY = "email";

	/**
	 * The key of the username object into the session.
	 */
	private final static String USERNAME_SESSION_KEY = "username";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.aurasphere.botmill.fb.autoreply.AutoReply#createResponse(co.aurasphere
	 * .botmill.fb.model.incoming.MessageEnvelope)
	 */
	@Override
	public FbBotMillResponse createResponse(MessageEnvelope envelope) {
		// Creates an unique session bound to the used id if it doesn't exist
		// yet.
		String userId = envelope.getSender().getId();

		// New session, greet the user. The check on the text is done to prevent
		// the get started button to proceed to the next steps when a user
		// deletes the conversation and presses it again.
		if (SESSION_MANAGER.getSessionData(userId,
				CURRENT_CONVERSATION_STEP_SESSION_KEY) == null
				|| safeGetMessage(envelope).isEmpty()) {
			// Creates a new session and saves the current step of the
			// conversation into it.
			SESSION_MANAGER.buildSession(userId);
			addSessionData(userId, CURRENT_CONVERSATION_STEP_SESSION_KEY,
					ConversationStep.ASK_EMAIL);

			return ReplyFactory
							.addTextMessageOnly(
									"Hi! I'm a simple BotMill demo bot! Do you mind sharing with me your email address?")
							.build(envelope);
		}

		// If it's not a new user then returns the next conversation step.
		return nextConversationStep(envelope, userId);
	}

	/**
	 * @param envelope
	 * @param userId
	 * @return
	 */
	private FbBotMillResponse nextConversationStep(MessageEnvelope envelope,
			String userId) {

		ConversationStep currentStep = (ConversationStep) SESSION_MANAGER
				.getSessionData(userId, CURRENT_CONVERSATION_STEP_SESSION_KEY)
				.getValue();
		String incomingMessage = safeGetMessage(envelope);
		switch (currentStep) {
		case ASK_EMAIL:
			// Second convo step: the session has been created, the user has
			// been asked for an email and he wrote it.

			// This is a good place for validation: you could check the
			// incoming message against your validator. If the validation
			// passes, you save the data and go on with the conversation.
			// Otherwise you ask it again. There are no validation on this demo
			// bot, so it assumes that any String you pass is your email.

			// Stores both the email and the current conversation step into
			// session.
			addSessionData(userId, EMAIL_SESSION_KEY, incomingMessage);
			addSessionData(userId, CURRENT_CONVERSATION_STEP_SESSION_KEY,
					ConversationStep.ASK_USERNAME);

			return ReplyFactory
							.addTextMessageOnly(
									"So your email is \""
											+ incomingMessage
											+ "\"? Great! I'd also like to know your name. Could you tell it to me?")
							.build(envelope);
		case ASK_USERNAME:

			// Same as above. Nice place to perform validation.
			addSessionData(userId, USERNAME_SESSION_KEY, incomingMessage);

			// This is a good place to store permanently the data into your
			// system before destroying the user session.

			// Destroys the session so a new conversation can be started the
			// next time the user interacts with the bot. The session doesn't
			// need to be destroyed if the bot wants to permanently "remember"
			// about this user and the data inserted. For instance, the next
			// time the user greets the bot, it may use the username already
			// stored from the session.
			SESSION_MANAGER.destroySession(userId);

			return ReplyFactory
							.addTextMessageOnly(
									incomingMessage
											+ " is a cool name! Now I have all the information that I need. Thank you for your patience. Now I'm going to close this session, so the next time you will talk to me I won't remember anything about you. Bye bye!")
							.build(envelope);
		default:
			// Never reached.
			return null;
		}
	}

	/**
	 * Adds some data to the user session.
	 * 
	 * @param sessionId
	 *            the session to which add data.
	 * @param key
	 *            the key of the data to add.
	 * @param value
	 *            the data to add.
	 */
	private static void addSessionData(String sessionId, String key,
			Object value) {
		KeyValuePair data = new KeyValuePair();
		data.setKey(key);
		data.setValue(value);
		SESSION_MANAGER.putSessionData(sessionId, data);
	}

}
