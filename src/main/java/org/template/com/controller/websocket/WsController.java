package org.template.com.controller.websocket;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.template.com.controller.BaseController;
import org.template.com.model.User;
import org.template.com.model.msg.WiselyMessage;
import org.template.com.model.msg.WiselyResponse;
import org.template.com.service.UserService;

@Controller
public class WsController extends BaseController{

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping("/prompt")
	public ResponseEntity<Map<String, Object>> handlePrompt(Principal principal) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<User> list = userService.findAll();
		for(User user : list) {
			messagingTemplate.convertAndSendToUser(user.getLoginName(), "/prompt/notifications", principal.getName() + "-sendMsg:" + "亮仔好开心、好快乐、已经高潮了！！！");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuc", true);
		return ResponseEntity.ok(map);
	}
	
}
