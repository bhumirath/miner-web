package th.ac.ku.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.restaurant.model.Menu;
import th.ac.ku.restaurant.service.MenuService;
import th.ac.ku.restaurant.dto.MenuDto;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

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
    public String getMenuForm(MenuDto menuDto) {
        return "menu-add";
    }

    @PostMapping("/add")
    public String addMenu(@ModelAttribute Menu menu, BindingResult result,
                          Model model) {
        if (result.hasErrors())
            return "menu-add";

        service.create(menu);
        return "redirect:/menu";
    }

}
