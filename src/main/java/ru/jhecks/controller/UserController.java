package ru.jhecks.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jhecks.model.User;
import ru.jhecks.service.RoleService;
import ru.jhecks.service.UserService;

@Controller
@ComponentScan("ru.jhecks")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String showUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("adminUser", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/admin/create")
    public String creat(@ModelAttribute("newUser") User user,
                        @RequestParam(value = "index", required = false) Long[] identifiers) {
        if (identifiers != null) {
            for (Long roleId : identifiers) {
                user.addRole(roleService.getRole(roleId));
            }
        } else {
            user.addRole(roleService.getRole(2L));
        }
        userService.createUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/update")
    public String update(@ModelAttribute("newUser") User user,
                         @RequestParam(value = "index", required = false) Long[] identifiers) {
        if (identifiers != null) {
            for (Long roleId : identifiers) {
                user.addRole(roleService.getRole(roleId));
            }
        } else {
            user.addRole(roleService.getRole(2L));
        }
        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

