package mvc.controller.study;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import mvc.model.WebSocketMap;
import mvc.service.GreetService;
import mvc.service.MemberService;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	GreetService greetService;

	@Autowired
	MemberService memberService;
	
	@Autowired
	WebSocketMap sessions;
	
	@RequestMapping(method = RequestMethod.GET)
	public String joinHandle(Model model, HttpSession session) {
		model.addAttribute("ment", greetService.make());
		return "join";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map<String, String> param, Model model, HttpSession session) throws IOException {
		boolean rst = memberService.addNewOne(param);
		if (rst) {
			session.setAttribute("logon", param.get("id"));
			
			List<WebSocketSession> s = (List<WebSocketSession>) sessions.get(session.getId());
			Map data = new HashMap();
			data.put("cnt", s.size());
			data.put("info", param.get("id") + "�� �������.");
			Gson gson = new Gson();
			for(WebSocketSession ws : s) {
				ws.sendMessage(new TextMessage(gson.toJson(data)));
			}
			
			return "redirect:/";
		} else {
			model.addAttribute("err", "������������ ������ �־����ϴ�.");
			return "join";
		}
	}

	@RequestMapping("/confirm")
	@ResponseBody
	public boolean confirmHandle(@RequestParam String id) {
		Map confirm = (Map) memberService.confirmId(id);
		BigDecimal bd = (BigDecimal)confirm.get("COUNT");
		return 0 == bd.intValue(); 
	}

}
