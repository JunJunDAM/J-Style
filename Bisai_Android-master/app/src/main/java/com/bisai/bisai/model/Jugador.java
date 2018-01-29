package com.bisai.bisai.model;

/**
 * Created by sergi on 27/03/2017.
 */

public class Jugador {


    private Long id;
    private String nickName;
    private User user;

    public Jugador(Long id, String nickName, User user) {
        this.id = id;
        this.nickName = nickName;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;

        if (!id.equals(jugador.id)) return false;
        if (!nickName.equals(jugador.nickName)) return false;
        return user.equals(jugador.user);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nickName.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
