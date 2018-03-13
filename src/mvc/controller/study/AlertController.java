package mvc.controller.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class AlertController extends TextWebSocketHandler{
	
	//	������ ���ؼ��� ������, ������ Ű�� �ؼ� ��� ������ �Ϸ��� ��.
	Map<String, List<WebSocketSession>> sessions;
	
	public Map<String, List<WebSocketSession>> getSessions() {
		return sessions;
	}

	@PostConstruct
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("mvc/controller/study/test.xml");
		sessions = (HashMap)ctx.getBean("sessions");
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//	System.out.println("AlertController afterConnectionEstablished");
		
		//	HttpSession�� �����ؼ� ������ ���;���. �׳��� �ȵǰ�..
		//	HttpSessionHandshakeInterceptor�� �����صθ�
		//	Spring�� �� �޼��带 ȣ���� ��, �� Ŭ���̾�Ʈ�� ������� HttpSession�� setAttribute
		//	�Ǿ��ִ� ������ WebSokcetSession���� �̾ƴ� �� �� �ְ� �־���.
		//	�׷��鼭 �߰���, "HTTP.SESSION.ID"��� Ű�� ������� session id�� �־���.
		
		Map<String, Object> map = session.getAttributes();
		
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID");
		if(!sessions.containsKey(key)) {
			sessions.put(key, new ArrayList<>());
		}
		sessions.get(key).add(session);
		
		Set<String> keys = sessions.keySet();
		for(String k : keys) {
			System.out.println("key : " + k + "/ value : " + sessions.get(k).size());
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//	System.out.println("AlertController afterConnectionClosed");
		
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID");
		sessions.get(key).remove(session);
		if(sessions.get(key).isEmpty()) {
			sessions.remove(key);
		}
	}

	
}
