package mvc.controller.study;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

/*
 * ws ����� �����ҿ뵵�� ��Ʈ�ѷ� ����
 * 	1. WebSocketHandler (I) �� implements �ɾ ������ �����ؼ� ���.
 * 	2. ������ �´� WebSocketHandler�� extends �ɾ ���
 * 		- TextWebSocketHandler  / BinaryWebSocketHandler
 * 
 *  WebSocket Handler �� ������, spring �������Ͽ�.
 */

@Controller("wsController")	//scan ���� ��ϵǴ� ���� Ŭ���������� ��ϵ�. �ٲܼ� ����.	 
public class WSController extends TextWebSocketHandler {
	
	Set<WebSocketSession> wsSessions;
	
	@PostConstruct	//	init-method
	public void init() {
		wsSessions = new LinkedHashSet<>();
	}

	@Override	//	Ŭ�������� ������ ����Ǿ��� ��
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//	System.out.println("afterConnectionEstablished.." + session.getId());
		//	System.out.println(session.getRemoteAddress().getAddress().getHostAddress());	//	������ IP�ּ�
		if(!wsSessions.contains(session)) {
			wsSessions.add(session);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("cnt", wsSessions.size());
		map.put("info", session.getRemoteAddress().getAddress().getHostAddress() + "���� �����մϴ�.");
		//	port�� �޶� cnt�� �ö�.
		//	Ȯ�� : map.put("info", session.getRemoteAddress().toString() + "���� �����մϴ�.");
				
		
		Gson gson = new Gson();
		
		for(WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}
		//	System.out.println("current size : " + wsSessions.size());
		
	}

	@Override	//	Ŭ�������� �޼����� ���� ��
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage.." + session +" / " + message);
	}

	@Override	//	Ŭ������ ������ ������ �� 	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//	System.out.println("afterConnectionClosed.." + session);
		wsSessions.remove(session);
		
		Map<String, Object> map = new HashMap<>();
		map.put("cnt", wsSessions.size());
		map.put("info", session.getRemoteAddress().getAddress().getHostAddress() + "���� ������ �����մϴ�.");
		Gson gson = new Gson();
		
		for(WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}
		
	}
}
