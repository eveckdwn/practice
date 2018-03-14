package mvc.model;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketMap extends LinkedHashMap<String, List<WebSocketSession>>{
	//	WebSocket에서 Socket들을 저장하는 Map을 따로 만듬.
}
