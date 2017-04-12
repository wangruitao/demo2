package org.template.com.controller.websocket;

import java.security.Principal;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.template.com.controller.BaseController;
import org.template.com.model.msg.WiselyMessage;
import org.template.com.model.msg.WiselyResponse;

@Controller
public class WsController extends BaseController{

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/ws/welcome")
	@SendTo("/ws/topic/getResponse")
	public WiselyResponse say(WiselyMessage msg) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("========" + msg.getName());
		return new WiselyResponse("Welcome," + msg.getName() + "!");
	}
	
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) {
		if(principal.getName().equals("tom")) {
			messagingTemplate.convertAndSendToUser("jok", "/queue/notifications", principal.getName() + "-send:" + msg);
		} else if(principal.getName().equals("jok")) {
			messagingTemplate.convertAndSendToUser("tom", "/queue/notifications", principal.getName() + "-send:" + msg);
		}
	}
	
}
