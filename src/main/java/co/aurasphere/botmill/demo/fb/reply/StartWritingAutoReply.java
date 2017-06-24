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

import co.aurasphere.botmill.fb.autoreply.AutoReply;
import co.aurasphere.botmill.fb.model.incoming.MessageEnvelope;
import co.aurasphere.botmill.fb.model.outcoming.FbBotMillResponse;
import co.aurasphere.botmill.fb.model.outcoming.action.TypingAction;
import co.aurasphere.botmill.fb.model.outcoming.factory.ReplyFactory;

/**
 * AutoReply which makes the bot start typing on Messenger.
 * 
 * @author Donato Rimenti
 */
public class StartWritingAutoReply extends AutoReply {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.aurasphere.botmill.fb.autoreply.AutoReply#createResponse(co.aurasphere
	 * .botmill.fb.model.incoming.MessageEnvelope)
	 */
	@Override
	public FbBotMillResponse createResponse(MessageEnvelope envelope) {
		return ReplyFactory.addTypingAction(TypingAction.TYPING_ON).build(
				envelope);
	}

}
