package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.restaurant.dto.MenuDto;
import th.ac.ku.restaurant.model.Menu;
import th.ac.ku.restaurant.model.Order;
import th.ac.ku.restaurant.repository.MenuRepository;
import th.ac.ku.restaurant.repository.UserRepository;
import th.ac.ku.restaurant.service.MenuService;
import th.ac.ku.restaurant.service.SignupService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @Autowired
    private SignupService signupService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

//    @GetMapping
//    public List<Menu> getAll(){
//        return service.getAll();
//    }
//
//    @PostMapping
//    public Menu create(@RequestBody Menu menu){
//        return service.create(menu);
//    }
//
//    @GetMapping("/{id}")
//    public Menu getMenuById(@PathVariable UUID id){
//        return service.getMenuById(id);
//    }
//
//    @PostMapping
//    public Menu update(@RequestBody Menu menu){
//        return service.update(menu);
//    }
//
//    @DeleteMapping("/{id}")
//    public Menu delete(@PathVariable UUID id){
//        return service.delete(id);
//    }
//
//    @GetMapping("/name/{name}")
//    public Menu getMenuByName(@PathVariable String name){
//        return service.getMenuByName(name);
//    }
//
//    @GetMapping("/category/{category}")
//    public List<Menu> getMenuByCategory(@PathVariable String category){
//        return service.getMenuByCategory(category);
//    }

    @GetMapping
    public String getMenus(Model model) {
        model.addAttribute("menus", service.getAll());
        return "menu";
    }

    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute("menu",new Menu());
        return "menu-add";
    }

    @GetMapping("/edit/{name}")
    public String getEditMenu(Model model,@PathVariable("name") String name) {
        Menu menu = menuRepository.findByName(name);
        model.addAttribute("menu",menu);
        return "menu-add";
    }

    @PostMapping("/add")
    public String addMenu(@Valid @ModelAttribute("menu") Menu menu, BindingResult result, Model model) {
        if (result.hasErrors())
            return "menu-add";
        //model.addAttribute("menu", menu);
        menuRepository.save(menu);
        return "redirect:/menu";
    }

    @GetMapping("/delete/{name}")
    public String deleteOrder(Model model,@PathVariable(value = "name") String name) {
        Menu menu = menuRepository.findByName(name);
        //Menu menu = menuRepository.getById(id);
        //Order order = orderRepository.getById(id);
        //workOrderRepository.findByOrderId(id);

        menuRepository.delete(menu);
        //workOrderRepository.deleteById(id);
        return "redirect:/menu";
    }

}
