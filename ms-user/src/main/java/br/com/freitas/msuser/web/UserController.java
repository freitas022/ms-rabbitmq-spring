package br.com.freitas.msuser.web;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.freitas.msuser.domain.User;
import br.com.freitas.msuser.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid UserDto dto) {
        var userModel = new User();
        BeanUtils.copyProperties(dto, userModel);
        return userService.save(userModel);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> findAllUser(Pageable pageable) {
    	return userService.findAllUser(pageable);
    }
}
