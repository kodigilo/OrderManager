package studio.genesis.manager.order.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping({"/"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IndexController {

    @GetMapping("/")
    @Operation(hidden = true)
    public String index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
        return null;
    }

}
