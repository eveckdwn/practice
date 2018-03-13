package mvc.controller.study;

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
import org.springframework.web.socket.WebSocketSession;

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
	AlertController alertController;
	
	@RequestMapping(method = RequestMethod.GET)
	public String joinHandle(Model model, HttpSession session) {
		model.addAttribute("ment", greetService.make());
		Map sessions = alertController.getSessions();
		System.out.println("sessions : " + sessions);
		List ws = (List)sessions.get(session.getId());
		System.out.println("ws : " + ws);
		return "join";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map<String, String> param, Model model, HttpSession session) {
		boolean rst = memberService.addNewOne(param);
		if (rst) {
			session.setAttribute("logon", param.get("id"));
			return "redirect:/";
		} else {
			model.addAttribute("err", "계정생성에서 문제가 있었습니다.");
			return "join";
		}
	}

	@RequestMapping("/confirm")
	@ResponseBody
	public String confirmHandle(@RequestParam String id) {
		return String.valueOf(memberService.confirmId(id));
	}

}
