package mvc.controller.study;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mvc.service.GreetService;
import mvc.service.MemberService;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	GreetService greetService;

	@RequestMapping(method = RequestMethod.GET)
	public String joinHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "join";
	}

	@Autowired
	MemberService memberService;

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
