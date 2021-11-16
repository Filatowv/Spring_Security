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
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
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



//	@GetMapping("/user")
//	public String userInfoPage(@AuthenticationPrincipal User user, Model model) {
//		model.addAttribute("user",user);
//		return "userInfo";
//	}

	@GetMapping(value = "/user")
	public String getUserInfo(@AuthenticationPrincipal User user, Model model){
		model.addAttribute("user", user);
		model.addAttribute("roles",user.getRoles());
		return "userInfo";
	}

	@GetMapping(value = "/admin")
	public String adminInfoPage(Model model) {
		model.addAttribute("userList",userService.getAllUsers());
		return "adminInfo";
	}

//	//список с новым пользователем
//	@PostMapping(value = "admin/save")
//	public String saveUser(@ModelAttribute("user") User user) {
//		userService.addUser(user);
//		return "redirect:/admin";
//	}
//
//	//форма нового пользователя
//	@GetMapping("admin/new")
//	public String newUser(@ModelAttribute("user") User user, Model model) {
//		List<Role> listRoles = roleService.getAllRole();
//		model.addAttribute("listRoles",listRoles);
//		return "newUser";
//	}

	@GetMapping(value = "/admin/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.getAllRole());
		return "new";
	}

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

	@RequestMapping("/admin/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}
//
//	//форма редактирования
//	@GetMapping("/admin/edit/{id}")
//	public String edit(Model model,
//					   @PathVariable("id")long id) {
//		model.addAttribute("user",userService.getUserById(id));
//		return "edit_user";
//	}

//	//запрос редактирования
//	@PatchMapping("/{id}")
//	public String update(@ModelAttribute("user") User user) {
//		userService.updateUser(user);
//		return "redirect:/admin";
//	}


//	//форма пользователя
//	@GetMapping("admin/new")
//	public String newUser(User user ,Model model) {
////		User user = new User();
//		model.addAttribute("user",user);
//		model.addAttribute("roles",roleService.getAllRole());
//		return "newUser";
//	}
//
//


//	//список с новым пользователем
//	@PostMapping("/create")
//	public String addUser(@RequestParam("idRoles")List<Long> idRoles,
//						  User user) {
//		Set<Role> roleList = new HashSet<>();
//		for(Long id:idRoles) {
//			roleList.add(roleService.findById(id));
//		}
//		user.setRoles(roleList);
//		userService.add(user);
//		return "redirect:/admin";
//	}



//изменяем модель Стринг(не коллекцию)
//	@RequestMapping("/admin/edit/{id}")
//	public ModelAndView showEditUser(@PathVariable(name = "id") long id) {
//		ModelAndView maw = new ModelAndView("edit_user");
//		User user = userService.getUserById(id);
//		maw.addObject("user",user);
//		return maw;
//	}

//
//	@ModelAttribute("roles")
//	public List<Role> listRole (){
//		return roleService.getAllRole();
//	}

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