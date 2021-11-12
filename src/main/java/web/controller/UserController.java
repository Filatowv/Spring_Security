package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


@Controller
public class UserController {

	private UserService userService;
	private RoleService roleService;

	@Autowired
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping("/user")
	public String userInfoPage(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user",user);
		return "userInfo";
	}


	@GetMapping(value = "/admin")
	public String adminHomePage(Model model) {
		model.addAttribute("userList",userService.getAllUsers());
		return "adminInfo";
	}


	@GetMapping("admin/new")
	public String showNewUser(User user ,Model model) {
//		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("roles",roleService.getAllRole());
		return "newUser";
	}

	@PostMapping(value = "admin/save")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/admin";
	}

	@RequestMapping("/admin/edit/{id}")
	public ModelAndView showEditUser(@PathVariable(name = "id") long id) {
		ModelAndView maw = new ModelAndView("edit_user");
		User user = userService.getUserById(id);
		maw.addObject("user",user);

		return maw;
	}

	@RequestMapping("/admin/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		userService.deleteUser(id);
		return "redirect:/admin";
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