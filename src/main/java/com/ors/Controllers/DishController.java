package com.ors.Controllers;

import com.ors.Models.Chef;
import com.ors.Models.Dish;
import com.ors.Models.Roles;
import com.ors.Security.MyUserDetails;
import com.ors.Services.ChefServices;
import com.ors.Services.DishServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/dish")
public class DishController {
    @Autowired private DishServices dishServices;
    @Autowired private ChefServices chefServices;
    @GetMapping("/newdish")
    public String getNewDish(Model model){
        model.addAttribute("dish",new Dish());
        return "newdish";
    }
    @PostMapping("/savedish")
    public String saveDish(@ModelAttribute("dish") Dish dish, RedirectAttributes rd, @RequestParam("fileImage")MultipartFile multipartFile, @AuthenticationPrincipal MyUserDetails userDetails) throws IOException {
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        dish.setImageUrl(fileName);
        Map<String, Roles> loged = new TreeMap<>();
        loged = userDetails.getLoggedEmailAndRole();
        String emailChef="";
        for(Map.Entry<String,Roles>entry:loged.entrySet()){
            emailChef=entry.getKey();
        }
        Chef chef = chefServices.getLoggedChef(emailChef);
        Dish savedDish = dishServices.addNewDishByChef(dish, chef);
        String uploadDir="./dish-logos/"+savedDish.getChef().getId();
        Path uploadPath= Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream=multipartFile.getInputStream()) {
            Path filepath=uploadPath.resolve(fileName);
            Files.copy(inputStream, filepath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw  new IOException("could not save file name "+ fileName);
        }
        if (savedDish!=null) {
            rd.addFlashAttribute("savedDish", "New Dish Added Successfully!!");
            return "redirect:/dish/menu";
        } else {
            rd.addFlashAttribute("FailedSaveDish", "Failed To Add New Dish !!");
            return "redirect:/dish/menu";
        }
    }
    @GetMapping("/menu")
    public String menu(Model model){
        List<Dish>dishes=dishServices.getAllMenu();
        model.addAttribute("dishes",dishes);
        return "menu";
    }
}
