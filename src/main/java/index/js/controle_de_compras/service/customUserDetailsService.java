package index.js.controle_de_compras.service;

import index.js.controle_de_compras.model.appUser;
import index.js.controle_de_compras.repository.userRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class customUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscando o usuário pelo nome de usuário
        appUser user = userRepository.findByUsername(username);
        
        // Se o usuário não for encontrado, lança uma exceção
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        List<GrantedAuthority> authorities = getAuthorities(user);

 
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities(appUser user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}