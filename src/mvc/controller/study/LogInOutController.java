package mvc.controller.study;

import java.io.IOException;
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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import mvc.model.WebSocketMap;
import mvc.service.GreetService;
import mvc.service.MemberService;

@Controller
@RequestMapping("/login")
public class LogInOutController {
	@Autowired
	GreetService greetService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String loginHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "login";
	}
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	WebSocketMap sessions;
	
	@RequestMapping(method=RequestMethod.POST)
	public String loginPHandle(@RequestParam Map map, HttpSession session, Model model) throws IOException {
		int result = memberService.loginCheck(map);
		switch(result) {
		case 0:
			session.setAttribute("logon", map.get("id"));
			List<WebSocketSession> s = (List<WebSocketSession>) sessions.get(session.getId());
			Map data = new HashMap();
			data.put("cnt", sessions.size());
			data.put("info", map.get("id") + "�� �������.");
			Gson gson = new Gson();
			for(WebSocketSession ws : s) {
				ws.sendMessage(new TextMessage(gson.toJson(data)));
			}
			return "redirect:/";
		case 1:
			model.addAttribute("ment", greetService.make());
			model.addAttribute("err", "�α����ϴ� �������� ������ �־����ϴ�.\n���̵� �����ϴ�.");
			return "login";
		case 2:
			model.addAttribute("ment", greetService.make());
			model.addAttribute("err", "�α����ϴ� �������� ������ �־����ϴ�.\n���̵� Ȥ�� ��й�ȣ�� Ȯ�����ּ���.");
			return "login";
		default:
			model.addAttribute("ment", greetService.make());
			model.addAttribute("err", "�α����ϴ� �������� ������ �־����ϴ�.");
			return "login";
		}
	}
	
	@RequestMapping("/out")
	public String logoutHandle(HttpSession session, Model model){
		session.removeAttribute("logon");
		model.addAttribute("ment", greetService.make());
		return "index";
	}

}
