package index.js.controle_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.UserDetails;

import index.js.controle_de_compras.service.customUserDetailsService;

@Controller
public class loginController {

    @Autowired
    private customUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            // Carregar o usuário
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            System.out.println("Senha armazenada no banco: " + userDetails.getPassword());
            System.out.println("Senha fornecida pelo usuário: " + password);

            // Comparar a senha fornecida com a senha armazenada no banco
            boolean isPasswordValid = passwordEncoder.matches(password, userDetails.getPassword());

            if (isPasswordValid) {
                // Senha correta, fazer o login ou redirecionar para a página principal
                return "redirect:/controle_de_compras/compras";
            } else {
                // Senha incorreta, retornar erro
                return "redirect:/login?error";
            }
        } catch (UsernameNotFoundException e) {
            // Caso o usuário não seja encontrado
            return "redirect:/login?error";
        }
    }
}
