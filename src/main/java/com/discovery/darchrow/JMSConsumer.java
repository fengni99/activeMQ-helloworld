/**
 * Copyright (c) 2016 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.discovery.darchrow;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消费者
 * @author dongliang.ma
 * @date 2016年11月2日下午3:15:53
 */
public class JMSConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionFactory connectionFactory =
				new ActiveMQConnectionFactory(Constant.USERNMAE, Constant.PASSWORD, Constant.BROKEURL);
		try {
			Connection conection =connectionFactory.createConnection();
			conection.start();
			Session session =conection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination =session.createQueue("HelloWorld");
			MessageConsumer consumer = session.createConsumer(destination);
			while(true){
				TextMessage receive = (TextMessage)consumer.receive(100000);
				if(receive !=null){
					System.out.println("收到消息：" +receive.getText());
				}else
					break;
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
