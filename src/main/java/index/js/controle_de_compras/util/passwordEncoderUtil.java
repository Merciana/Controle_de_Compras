package index.js.controle_de_compras.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEncoderUtil {
    
        public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

         // Hashes armazenados no banco
         String[] hashes = {
            "$2a$10$R2x8rOUlq./Il6vHpQKNr.iZ8jZ4e9PfziUG0rms6Nfq5cRZjXMGG", // Senha: Tster021
            "$2a$10$kA2uAqIZd7lB6w35AaZFtTGo6m49xFsjI8oTu6EfvJ6XW5rh0nTeG", // Senha: Mster021
            "$2a$10$T5YP7h35PpOdE5g8ZGq0RUqggqxzXYkMZXYr7kfeE7cMy7Z8Q5zdy", // Senha: Cster021
            "$2a$10$FiOg7mnDeMZGfw6HrXo2TQiKkNmq5sOZiYBq56.PaeXrbXf7LzD8a", // Senha: TIster021
            "$2a$10$XjH5Fhftog8WXQOXw1qJ0uZyhwKPxVUnZToSCpPEsYpGRX9r5jKkC", // Senha: portaria
            "$2a$10$QqNqjOjpoOZbAo4fUceLMpnj..EnTEYrM7slFzS9Us9aF7RwwL9CC"  // Senha: admin!@#
        };

        // Substitua pelas senhas dos usuários
        String[] usersPasswords = {
            "Tster021", // Karla Lidiane
            "Mster021", // Diego
            "Cster021", // Yure
            "TIster021", // Carlos
            "portaria", // Portaria
            "admin!@#" // Admin
        };

        for (int i = 0; i < hashes.length; i++) {
            boolean isMatch = passwordEncoder.matches(usersPasswords[i], hashes[i]);
            System.out.println("Senha '" + usersPasswords[i] + "' corresponde ao hash? " + isMatch);
        }
           

        String testPassword = "Tster021";
        String testHash = "$2a$10$R2x8rOUlq./Il6vHpQKNr.iZ8jZ4e9PfziUG0rms6Nfq5cRZjXMGG";
        boolean matches = passwordEncoder.matches(testPassword, testHash);
        System.out.println("\nSenha '" + testPassword + "' corresponde ao hash? " + matches);
    }
}
