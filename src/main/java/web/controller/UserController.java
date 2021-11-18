package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {


	private final UserService userService;
	private final RoleService roleService;


	@Autowired
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}


	//список пользователя
	@GetMapping(value = "/user")
	public String getUserInfo(@AuthenticationPrincipal User user, Model model){
		model.addAttribute("user", user);
		model.addAttribute("roles",user.getRoles());
		return "user_Info";
	}


	//список всех пользователей
	@GetMapping(value = "/admin")
	public String adminInfoPage(Model model) {
		model.addAttribute("userList",userService.getAllUsers());
		return "admin_Info";
	}


	//форма нового пользователя
	@GetMapping(value = "/admin/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.getAllRole());
		return "new_user";
	}


	//список с новым пользователем
	@PostMapping(value = "/admin/add")
	private String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
		Set<Role> roles = new HashSet<>();
		for (String role : checkBoxRoles) {
			roles.add(roleService.getRoleByName(role));
		}
		user.setRoles(roles);
		userService.addUser(user);
		return "redirect:/admin";
	}


//	форма редактирования
	@GetMapping("/admin/edit/{id}")
	public String edit(@PathVariable("id")long id,Model model) {
		model.addAttribute("user",userService.getUserById(id));
		model.addAttribute("role",roleService.getAllRole());
		return "edit_user";
	}


	//запрос редактирования
	@PatchMapping("/admin/{id}")
	public String update(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/admin";
	}


	// удаление
	@RequestMapping("/admin/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}
}