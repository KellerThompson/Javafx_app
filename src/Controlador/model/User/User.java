package Controlador.model.User;

import Controlador.model.Persona.Persona;

public class User
{
    private int id;
    private Persona persona;
    private String username;
    private String password;
    private String fechaRegistro;

    public User(int id, Persona persona, String username, String password, String fechaRegistro) {
        this.id = id;
        this.persona = persona;
        this.username = username;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Persona=" + persona.toString() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
