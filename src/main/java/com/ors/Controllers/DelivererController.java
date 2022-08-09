package com.ors.Controllers;

import com.ors.Models.Customer;
import com.ors.Models.Dish;
import com.ors.Models.DishOrder;
import com.ors.Models.Order;
import com.ors.Services.CustomerServices;
import com.ors.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/delivery")
public class DelivererController {
    @Autowired private OrderServices orderServices;
    @Autowired private CustomerServices customerServices;
    @GetMapping("/allorders")
    public String getReadyOrders(Model model)
    {
        List<Order>orders=orderServices.getReadyOrders();
        model.addAttribute("orders",orders);
        return "readyorders";
    }
    @GetMapping("/deliverorder")
    public String takeOrder(@RequestParam("orderId")int orderId, RedirectAttributes rd){
        Order order=orderServices.getOrderByIDd(orderId);
        order.setStatus(1);
        boolean newOrderStatus=orderServices.saveOrder(order);
        if(newOrderStatus){
            rd.addFlashAttribute("Delivered","Order Delivered Successfully !!");
            return "redirect:/delivery/allorders";
        }
        else{
            rd.addFlashAttribute("FaildDelivered","Faild to deliver order !!");
            return "redirect:/delivery/allorders";
        }
    }
}
