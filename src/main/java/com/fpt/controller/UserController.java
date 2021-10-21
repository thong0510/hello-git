package com.fpt.controller;

import com.fpt.entities.User;
import com.fpt.model.UserDTO;
import com.fpt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    private static final String CREATE_USER = "/user/add";
    private static final String LIST_USER = "/user/list";
    private static final String EDIT_USER = "/user/edit";
    private static final String DELETE_USER = "/user/delete";
    private static final String DASH_BOARD = "/";
    private static final String LOGIN = "/login";

    private static final String CREATE_USER_VIEW = "user/addUser";
    private static final String LIST_USER_VIEW = "user/list-user";
    private static final String EDIT_USER_VIEW = "user/edit-user";
    private static final String HOME_PAGE = "home-page";
    private static final String LOGIN_PAGE = "login";

    @Autowired
    private UserService userService;

    @GetMapping(DASH_BOARD)
    public String trangchu(HttpServletRequest request) {
        String msg = "Hello ";
        request.setAttribute("msg", msg);
        return HOME_PAGE;
    }

    @GetMapping(LOGIN)
    public String login(HttpServletRequest request, @RequestParam(name = "e", required = false) String error ) {
        if (error != null) {
            request.setAttribute("e", error);
        }
        return LOGIN_PAGE;
    }

    @GetMapping(CREATE_USER)
    public String addUserGet() {
        return CREATE_USER_VIEW;
    }

    @PostMapping(CREATE_USER)
    public String addUserPost(@ModelAttribute(name = "userDTO") UserDTO userDTO) {
        userDTO.setEnabled(true);
        userService.create(userDTO);
        return "redirect:" + LIST_USER;
    }

    @GetMapping(LIST_USER)
    public String listUser(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserDTO> users = userService.getAll();
        request.setAttribute("users", users);
        return LIST_USER_VIEW;
    }

    @GetMapping(EDIT_USER)
    public String editUser(Model model, @RequestParam(name = "id") Long id) {
        UserDTO userDTO = userService.getById(id);
        model.addAttribute("user", userDTO);
        return EDIT_USER_VIEW;

    }

    @PostMapping(EDIT_USER)
    public String editUserPost(@ModelAttribute(name = "user") UserDTO userDTO) {
        userService.update(userDTO);
        return "redirect:" + LIST_USER;
    }

    @GetMapping(DELETE_USER)
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return "redirect:" + LIST_USER;
    }
}
