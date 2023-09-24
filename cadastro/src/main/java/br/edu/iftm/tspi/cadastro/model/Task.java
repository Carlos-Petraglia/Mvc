package br.edu.iftm.tspi.cadastro.model;

public class Task {

    public Task(Long id, String name, String email, String nick) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nick = nick;
    }

    public Task() {

    }

    private Long id;
    private String name;
    private String email;
    private String nick;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

}
