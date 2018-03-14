package mvc.controller.study;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import mvc.model.WebSocketMap;

@Controller("alertController")
public class AlertController extends TextWebSocketHandler {

	// ������ Ŀ�ؼ��� ������, ������ Ű�� �ؼ� ��� ������ �Ϸ��� ��.
	@Autowired
	WebSocketMap sessions;
	

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// HttpSession �� �����ؼ� ������ ���;� ��..�׳��� �ȵǰ�..
		// HttpSessionHandshakeInterceptor �� �����صθ�,
		// Spring�� �� �޼��带 ȣ���Ҷ�, �� Ŭ���̾�Ʈ�� ������� HttpSession�� setAttribute
		// �Ǿ��ִ� ������ WebSokcetSession���� �̾ƴ� ���� �ְ� �־���.
		// �׷��鼭 �߰���. "HTTP.SESSION.ID" ��� Ű�� ������� session id�� �־��ְ�.
		
		System.out.println("AlertController.connectionEstablished");
		Map<String, Object> map = session.getAttributes();
		System.out.println(map);
		String key = (String) session.getAttributes().get("HTTP.SESSION.ID");
		if (!sessions.containsKey(key))
			sessions.put(key, new ArrayList<>());
		sessions.get(key).add(session);
		
		for(String k : sessions.keySet() ) {
			int size = sessions.get(k).size();
			System.out.println("�� " + k + " / ("+size+")" );
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String key = (String) session.getAttributes().get("HTTP.SESSION.ID");
		System.out.println(key);
		System.out.println(sessions);
		System.out.println(sessions.get(key));
		
		sessions.get(key).remove(session);
		if (sessions.get(key).isEmpty()) {
			sessions.remove(key);
		}
	}

}
