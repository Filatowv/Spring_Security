package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String printWelcome(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("I'm Spring MVC-SECURITY application");
//		messages.add("5.2.0 version by sep'19 ");
//		model.addAttribute("messages", messages);
//		return "hello";
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		model.addAttribute("userList",userService.getAllUsers());
		return "index";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }







//	@GetMapping()
//	public String getAllUsers(Model model) {
//		model.addAttribute("listUsers", userService.allUser());
//		return "list";
//	}
//
//
//	@GetMapping("/{id}")
//	public String findId(@PathVariable("id")Long id, Model model) {
//		model.addAttribute("user", userService.getById(id));
//		return "show";
//	}
//
//
//	@GetMapping("/new")
//	public String newUser(Model model) {
//		model.addAttribute("newUser",new User());
//		return "create";
//	}
//
//
//	@PostMapping()
//	public String addUser(@ModelAttribute("user") User user) {
//		userService.addUser(user);
//		return "redirect:/users";
//	}
//
//
//	@GetMapping("/{id}/edit")
//	public String edit(Model model,@PathVariable("id")long id) {
//		model.addAttribute("user",userService.getById(id));
//		return "edit";
//	}
//
//
//	@PatchMapping("{id}")
//	public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
//		userService.update(id,user);
//		return "redirect:/users";
//	}
//
//
//	@DeleteMapping("delete/{id}")
//	public String delete(@PathVariable("id")long id) {
//		userService.remove(id);
//		return "redirect:/users";
//	}

}