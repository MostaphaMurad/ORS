package com.ors.Controllers;

import com.ors.Models.*;
import com.ors.Security.MyUserDetails;
import com.ors.Services.CustomerServices;
import com.ors.Services.DishOrderServices;
import com.ors.Services.DishServices;
import com.ors.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired private CustomerServices customerServices;
    @Autowired private DishOrderServices dishOrderServices;
    @Autowired private OrderServices orderServices;
    @Autowired private DishServices dishServices;
    @GetMapping("/dish/viewdetails/{dishId}")
    public String getDish(@PathVariable("dishId")int id, Model model, RedirectAttributes rd){
        Dish dish=dishServices.getDishById(id);
        Chef chef=dish.getChef();
        if(dish==null){
            rd.addFlashAttribute("InvalidDish","Cant Find This Dish");
            return "/dish/menu";
        }
        else{
            model.addAttribute("dish",dish);
            return "dishdetails";
        }
    }
    @PostMapping("/dish/order")
    public String OrderDish(@RequestParam("dishId")int id, @RequestParam(value = "quantity",required = false)int quantity,RedirectAttributes rd,
                            @AuthenticationPrincipal MyUserDetails userDetails){
        if(userDetails==null)return "redirect:/login";
        Dish dish=dishServices.getDishById(id);
        List<DishOrder> dishOrder=new ArrayList<>();
        Map<String,Roles>custInfo=userDetails.getLoggedEmailAndRole();
        String loggedEmail="" ;
        for(Map.Entry<String,Roles>entry:custInfo.entrySet()){
            loggedEmail=entry.getKey();
            break;
        }
        Customer loggedCustomer=customerServices.getCustomerByEmail(loggedEmail);
        DishOrder dishOrder1=new DishOrder();
        for(int i=0;i<quantity;i++)
        {
            dishOrder1.setDish(dish);
            dishOrder.add(dishOrder1);
        }
        dishOrder1.setQuantity(quantity);
        Order order=new Order();
        order.setStatus(0);
        order.setDishOrders(dishOrder);
        order.setCustomer(loggedCustomer);
        boolean savedOrder=orderServices.saveOrder(order);
        if(savedOrder){
            dishOrder1.setOrder(order);
        }
        else{
            rd.addFlashAttribute("FailedSaveOrder","Cant Make Order");
            return "redirect:/dish/menu";
        }
        boolean saveDishOrder=dishOrderServices.addDishOrder(dishOrder1);
        if(saveDishOrder){
            rd.addFlashAttribute("SavedOrder","Order Saved Successfully!!");
            return "redirect:/dish/menu";
        }
        else{
            rd.addFlashAttribute("FailedSaveOrder","Cant Make Order");
            return "redirect:/dish/menu";
        }
    }
    @GetMapping("/getmyorders")
    public String getMyOrders(Model model,@AuthenticationPrincipal MyUserDetails userDetails){
        if(userDetails==null)return "redirect:/login";
        Map<String,Roles>customerLogged=userDetails.getLoggedEmailAndRole();
        String email="";
        for(Map.Entry<String,Roles>entry:customerLogged.entrySet()){
            email=entry.getKey();break;
        }
        Customer customer=customerServices.getCustomerByEmail(email);
        int customerId=customer.getId();
        List<Order> orders=orderServices.getOrdersByCustomerId(customerId);
        model.addAttribute("myorders",orders);
        return "customerorders";
    }
    @GetMapping("/recived")
    public String recivedOrder(Model model,@RequestParam("orderId")int orderId,RedirectAttributes rd,@AuthenticationPrincipal MyUserDetails userDetails){
        Order order=orderServices.getOrderByIDd(orderId);
        Map<String,Roles>custLogged=userDetails.getLoggedEmailAndRole();
        String emailCustomer="";
        for(Map.Entry<String,Roles>entry:custLogged.entrySet()){
            emailCustomer=entry.getKey();break;
        }
   /*     List<Order>orders=orderServices.getallOrders();
        List<DishOrder>dishOrders=dishOrderServices.getAllDishOrders();
        for(Order o:orders){
            if(o.equals(order)){
                dishOrders.remove(order);
            }
        }*/
        boolean deleteOrderForCustomer=orderServices.deleteOrderById(orderId);
        if(deleteOrderForCustomer){
            rd.addFlashAttribute("OrderRecived","You Recived The Order");
            return "redirect:/customer/getmyorders";
        }
        else{
            rd.addFlashAttribute("orderRecivedFaild","Faild Recived Operation");
            return "redirect:/customer/getmyorders";

        }
    }
}
