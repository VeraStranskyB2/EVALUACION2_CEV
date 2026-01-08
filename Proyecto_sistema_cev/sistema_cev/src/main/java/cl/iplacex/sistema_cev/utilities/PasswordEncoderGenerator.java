package cl.iplacex.sistema_cev.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] arg) {

        PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(PasswordEncoder.encode("password"));
    }

}
