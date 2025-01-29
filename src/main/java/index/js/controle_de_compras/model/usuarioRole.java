package index.js.controle_de_compras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.IdClass;

@Entity
@Table(name = "usuario_role")
@IdClass(usuarioRole.class)
public class usuarioRole implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private appUser usuario;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private role role;

    public usuarioRole() {}

    public usuarioRole(appUser usuario, role role) {
        this.usuario = usuario;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        usuarioRole that = (usuarioRole) o;
        return usuario.equals(that.usuario) && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, role);
    }

    public appUser getUsuario() {
        return usuario;
    }

    public void setUsuario(appUser usuario) {
        this.usuario = usuario;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }
    
}
