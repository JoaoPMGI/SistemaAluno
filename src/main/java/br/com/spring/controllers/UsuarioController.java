package br.com.spring.controllers;

import br.com.spring.dao.UsuarioDAO;
import br.com.spring.exceptions.ServiceExc;
import br.com.spring.models.Aluno;
import br.com.spring.models.Usuario;
import br.com.spring.service.UsuarioService;
import br.com.spring.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView login = new ModelAndView();
        login.setViewName("/login/login");
        login.addObject("usuario", new Usuario());
        return login;
    }

    @PostMapping("/logar")
    public ModelAndView logar(Usuario usuario, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView logar = new ModelAndView();
        logar.addObject("usuario", new Usuario());

        Usuario usuarioLogin = usuarioService.loginUsuario(usuario.getUsername(), Util.md5(usuario.getSenha()));

        if(usuarioLogin == null){
            logar.addObject("msg", "Usuário não encontrado");

        } else {
            session.setAttribute("usuarioLogado", usuarioLogin);
            return index();
        }

        return logar;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView login = new ModelAndView();
        login.setViewName("/login/cadastro");
        login.addObject("usuario", new Usuario());
        return login;
    }

    @PostMapping("/salvar-usuario")
    public ModelAndView salvar(Usuario usuario) throws Exception{
        ModelAndView salvar = new ModelAndView();
        usuarioService.salvarUsuario(usuario);
        salvar.setViewName("redirect:/");
        return salvar;
    }

    @GetMapping("/home")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView();
        index.setViewName("/index.html");
        index.addObject("aluno", new Aluno());
        return index;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession httpSession){
        httpSession.invalidate();
        return login();
    }
}
