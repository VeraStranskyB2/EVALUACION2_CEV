package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.RegistroDTO;
import cl.iplacex.sistema_cev.model.Usuario;

public interface IAuthService {
   
    Usuario registrarUsuario(RegistroDTO registroDTO);
}