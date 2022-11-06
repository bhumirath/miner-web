package th.ac.ku.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.restaurant.dto.MenuDto;
import th.ac.ku.restaurant.model.Menu;
import th.ac.ku.restaurant.repository.MenuRepository;
import th.ac.ku.restaurant.security.JwtAccessTokenService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtAccessTokenService tokenService;

    @Value("${api.book.url}")
    private String url;

    public List<Menu> getAll(){
        return menuRepository.findAll();
    }

    public Menu create(Menu menu){
        menuRepository.save(menu);
        return menu;
    }

    public Menu getMenuById(int id) {
        return menuRepository.findById(id).get();
    }

    public Menu update(Menu requestBody) {
        int id = requestBody.getId();
        Menu record = menuRepository.findById(id).get();
        record.setName(requestBody.getName());
        record.setPrice(requestBody.getPrice());
        record.setCategory(requestBody.getCategory());

        record = menuRepository.save(record);
        return record;
    }

    public Menu delete(int id) {
        Menu record = menuRepository.findById(id).get();
        menuRepository.deleteById(id);
        return record;
    }

    public Menu getMenuByName(String name) {
        return menuRepository.findByName(name);
    }

    public List<Menu> getMenuByCategory(String category) {
        return menuRepository.findByCategory(category);
    }

    public List<MenuDto> getMenus() {

        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8090/menu";

        ResponseEntity<MenuDto[]> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        entity, MenuDto[].class);

        MenuDto[] menus = response.getBody();
        return Arrays.asList(menus);
    }
    public MenuDto addMenu(MenuDto menu) {

        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(menu,headers);

        String url = "http://localhost:8090/menu";

        ResponseEntity<MenuDto> response =
                restTemplate.exchange(url, HttpMethod.POST,
                        entity, MenuDto.class);

        return response.getBody();
    }
}
