package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import product.enumeration.Role;
import product.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String registrationPage() {
		return "registration";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registration(@RequestParam(value="login") String login, @RequestParam(value="password") String password) {
		userService.save(login, password);
		return "redirect:/";
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String usersPage(Model model) {
		model.addAttribute("usersList", userService.getAll());
		model.addAttribute("rolesList", Role.getAll());
		return "users";
	}

	@RequestMapping(value="/edit-role", method=RequestMethod.POST)
	public String editRole(@RequestParam(value="login") String login, @RequestParam(value="role") String role) {
		userService.editRole(login, role);
		return "redirect:/users";
	}

}
