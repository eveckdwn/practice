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

import mvc.service.MemberService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping(method=RequestMethod.GET)
	public String loginHandle() {
		return "login";
	}
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String loginPHandle(@RequestParam Map map, HttpSession session, Model model) {
		int result = memberService.loginCheck(map);
		switch(result) {
		case 0:
			session.setAttribute("logon", map.get("id"));
			return "redirect:/";
		case 1:
			model.addAttribute("err", "로그인하는 과정에서 문제가 있었습니다.\n아이디가 없습니다.");
			return "login";
		case 2:
			model.addAttribute("err", "로그인하는 과정에서 문제가 있었습니다.\n아이디 혹은 비밀번호를 확인해주세요.");
			return "login";
		default:
			model.addAttribute("err", "로그인하는 과정에서 문제가 있었습니다.");
			return "login";
		}
	}
	

}
