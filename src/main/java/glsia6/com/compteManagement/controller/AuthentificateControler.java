package glsia6.com.compteManagement.controller;

import glsia6.com.compteManagement.serviceImpl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class AuthentificateControler {

    @Autowired
    private ClientService clientService;

    @GetMapping("/protected")
    public String Connectprotected() {
        return "Bienvenu dans notre api";
    }

    @GetMapping("/public")
    public String ConnectPublic() {
        return "Bienvenu dans notre api";
    }

}
