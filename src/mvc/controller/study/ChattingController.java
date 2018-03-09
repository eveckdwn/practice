package mvc.controller.study;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.service.MemberService;

@Controller
@RequestMapping("/chat")
public class ChattingController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String MakeChatroom(HttpSession session, Model model) {
		Map member = memberService.readId((String)session.getAttribute("logon"));
		model.addAttribute("lv", member.get("LV"));
		return "makeChatroom";
	}
}
