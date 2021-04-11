package com.Market.NFT.AdminPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
      return adminRepository.findAll();
    }

    public Admin modifyAdmin(Long id, Admin admin) {
      Optional<Admin> a = adminRepository.findById(id);
      if(a.isPresent()) {
        Admin adminModify = a.get();
        if(admin.getUserName() != null)
          adminModify.setUserName(admin.getUserName());
        if(admin.getPassword() != null)
          adminModify.setPassword(admin.getPassword());
        if(admin.getEmail() != null)
          adminModify.setEmail(admin.getEmail());
        return adminRepository.save(adminModify);
      }
      throw new IllegalStateException("admin with id: " + id + " not exist");
    }
}
