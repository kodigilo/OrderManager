package studio.genesis.manager.order.ordermanager.controllers;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "Index")
@RestController
@RequestMapping({"/"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IndexController {

    @GetMapping("/")
    public String index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
        return null;
    }

}
