package mvc.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.service.GreetService;

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

}
