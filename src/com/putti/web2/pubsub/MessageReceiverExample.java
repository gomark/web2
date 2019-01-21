package com.putti.web2.pubsub;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class MessageReceiverExample implements MessageReceiver {

	@Override
	public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
		System.out.println("messageId:" + message.getMessageId());
		System.out.println("Data:" + message.getData().toStringUtf8());
		
		for (String key : message.getAttributesMap().keySet()) {
			System.out.println("key=" + key + ", value=" + message.getAttributesMap().get(key).toString());
		}
	}

}
