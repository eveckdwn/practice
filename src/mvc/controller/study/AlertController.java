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
	
	//	웹소켓 컨넥션이 열릴때, 세션을 키로 해서 묶어서 관리를 하려고 함.
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
		
		//	HttpSession을 접근해서 정보를 얻어와야함. 그냥은 안되고..
		//	HttpSessionHandshakeInterceptor를 설정해두면
		//	Spring이 이 메서드를 호출할 때, 이 클라이언트가 사용중인 HttpSession의 setAttribute
		//	되어있는 값들을 WebSokcetSession에서 뽑아다 쓸 수 있게 넣어줌.
		//	그러면서 추가로, "HTTP.SESSION.ID"라는 키로 사용중이 session id도 넣어줌.
		
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
