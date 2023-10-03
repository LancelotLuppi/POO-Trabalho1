package application.domain.entidades.usuario;

import application.domain.entidades.Entity;

public class Usuario extends Entity {
    protected String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
