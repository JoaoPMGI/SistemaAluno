package br.com.spring.service;

import br.com.spring.dao.UsuarioDAO;
import br.com.spring.exceptions.CriptoExistException;
import br.com.spring.exceptions.EmailExistsException;
import br.com.spring.exceptions.ServiceExc;
import br.com.spring.models.Usuario;
import br.com.spring.util.Util;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioRepository;

    public void salvarUsuario(Usuario usuario) throws Exception {
        try{

            if(usuarioRepository.findByEmail(usuario.getEmail()) != null){
                throw new EmailExistsException("Email já cadastrado: " + usuario.getEmail());
            }

            usuario.setSenha(Util.md5(usuario.getSenha()));

        } catch (NoSuchAlgorithmException e){
            throw new CriptoExistException("Erro na criptografia da senha!");
        }

        usuarioRepository.save(usuario);
    }

    public Usuario loginUsuario(String usuario, String senha) throws ServiceExc {

        Usuario usuarioLogin = usuarioRepository.login(usuario, senha);
        return usuarioLogin;
    }

}
