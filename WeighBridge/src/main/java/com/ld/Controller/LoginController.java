package com.ld.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ld.Dao.LoginRepository;
import com.ld.Dao.RoleMasterRepository;
import com.ld.Entity.LoginEntity;
import com.ld.Entity.RoleMaster;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    @Autowired
    LoginRepository repo;

    @Autowired
    RoleMasterRepository repo1;

    @PostMapping("/check")
    public String authenticate(@RequestParam("role") String role, @RequestParam("userId") String userId,
            @RequestParam("password") String password) {
        RoleMaster user = repo1.findByRoleName(role);
        LoginEntity login = repo.checkCredential(user.getRoleId());
        if (login == null) {
            return "Wrong credentials!";
        } else {
            return "Login successful!";
        }
    }
}
