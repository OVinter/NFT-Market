package com.Market.NFT.AdminPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public List<Admin> getAllAdmins() {
      return adminService.getAllAdmins();
    }

    @PutMapping("{id}")
    public Admin modifyAdmin(@PathVariable Long id, @RequestBody Admin admin) {
      return adminService.modifyAdmin(id, admin);
    }
}
