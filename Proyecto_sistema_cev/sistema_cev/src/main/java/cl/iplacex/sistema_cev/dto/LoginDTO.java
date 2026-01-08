package cl.iplacex.sistema_cev.dto;

public class LoginDTO {

    private String rutusuarioOrEmail;
    private String userPassword;

    public String getRutUsuarioOrEmail(){
        return rutusuarioOrEmail;
    } 

    public void setRutUsuarioOrEmail(String rutusuarioOrEmail){
        this.rutusuarioOrEmail = rutusuarioOrEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
