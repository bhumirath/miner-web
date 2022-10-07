package th.ac.ku.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.restaurant.dto.MenuDto;
import th.ac.ku.restaurant.security.JwtAccessTokenService;

import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtAccessTokenService tokenService;

    @Value("${api.book.url}")
    private String url;

    public List<MenuDto> getMenus() {

        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);

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

        ResponseEntity<MenuDto> response =
                restTemplate.exchange(url, HttpMethod.POST,
                        entity, MenuDto.class);

        return response.getBody();
    }
}
