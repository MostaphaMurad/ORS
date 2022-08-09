package com.ors.Controllers;
import com.ors.Models.Customer;
import com.ors.Models.Roles;
import com.ors.Security.MyUserDetails;
import com.ors.Services.CustomerServices;
import com.ors.Services.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class homeController {
    @Autowired private CustomerServices customerServices;
    @Autowired private RolesServices rolesServices;
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/login")
    public String loginPage(@AuthenticationPrincipal MyUserDetails userDetails){
        if(userDetails!=null)return "redirect:/dish/menu";
        return "login";
    }
    @GetMapping("/register")
    public String getCustomerDetails(Model model){
        model.addAttribute("customer",new Customer());
        Roles role=rolesServices.getCustomerRole("CUSTOMER");
        String roleName=role.getRoleName();
        int roleId=role.getId();
        model.addAttribute("roleName",roleName);
        model.addAttribute("roleId",roleId);
        return "addcustomer";
    }
    @PostMapping("/savcustomer")
    public String saveCustomer(@ModelAttribute("customer")Customer customer, RedirectAttributes rd){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String plainPassword=customer.getPassword();
        String encPassword=encoder.encode(plainPassword);
        customer.setPassword(encPassword);
        Customer SavedCustomer=customerServices.addNewCustomer(customer);
        if(SavedCustomer!=null){
            rd.addFlashAttribute("savedCustomer","Registered Successfully!!");
            return "redirect:/login";
        }
        else{
            rd.addFlashAttribute("FaildRegister","Regestration Faild!!");
            return "redirect:/register";
        }
    }
}
