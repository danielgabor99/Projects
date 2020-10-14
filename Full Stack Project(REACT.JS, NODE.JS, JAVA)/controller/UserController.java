package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.User;
import ubb.project.iss.service.ServiceInterface;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    private ServiceInterface<User> userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<User> getUsers() {
        return userService.getAll();
    }
}
