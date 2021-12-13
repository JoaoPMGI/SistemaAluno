package br.com.spring.controllers;

import br.com.spring.dao.AlunoDAO;
import br.com.spring.models.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class AlunoController {

    @Autowired // injeção de dependencia
    private AlunoDAO alunoRepository;

    @GetMapping("/cadastro-aluno")
    public ModelAndView cadastro(Aluno aluno){
        ModelAndView cadastro = new ModelAndView();
        cadastro.setViewName("/aluno/cadastro-aluno.html");
        cadastro.addObject("aluno", new Aluno());
        return cadastro;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Aluno aluno){
        ModelAndView cadastrar = new ModelAndView();

        if(aluno.getNome().equals("") || aluno.getMatricula().equals("")){
            cadastrar.setViewName("redirect:/cadastro-aluno");
            cadastrar.addObject("aluno");

        } else {
            cadastrar.setViewName("redirect:/lista-alunos");
            alunoRepository.save(aluno);
        }

        return cadastrar;
    }

    @GetMapping("/lista-alunos")
    public ModelAndView lista(){
        ModelAndView lista = new ModelAndView();
        lista.setViewName("/aluno/lista-alunos");
        lista.addObject("aluno", new Aluno());
        lista.addObject("listaAlunos", alunoRepository.findAll());
        return lista;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id){
        ModelAndView alterar = new ModelAndView();
        alterar.setViewName("/aluno/alterar-aluno");
        Aluno aluno = alunoRepository.getById(id);
        alterar.addObject("aluno", aluno);
        return alterar;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Aluno aluno){
        ModelAndView alteracao = new ModelAndView();
        alunoRepository.save(aluno);
        alteracao.setViewName("redirect:/lista-alunos");
        return alteracao;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id){
        alunoRepository.deleteById(id);
        return "redirect:/lista-alunos";
    }

    @GetMapping("/filtrar-aluno")
    public ModelAndView filtro(){
        ModelAndView filtro = new ModelAndView();
        filtro.setViewName("/aluno/filtro-aluno.html");
        filtro.addObject("aluno", new Aluno());
        return filtro;
    }

    @GetMapping("/alunos-ativos")
    public ModelAndView listaAlunosAtivos(){
        ModelAndView lista = new ModelAndView();
        lista.setViewName("/aluno/alunos-ativos");
        lista.addObject("aluno", new Aluno());
        lista.addObject("alunosAtivos", alunoRepository.findByStatusAtivo());
        return lista;
    }

    @GetMapping("/alunos-inativos")
    public ModelAndView listaAlunosInativos(){
        ModelAndView lista = new ModelAndView();
        lista.setViewName("/aluno/alunos-inativos");
        lista.addObject("alunosInativos", alunoRepository.findByStatusInativo());
        lista.addObject("aluno", new Aluno());
        return lista;
    }

    @GetMapping("/alunos-cancelados")
    public ModelAndView listaAlunosCancelados(){
        ModelAndView lista = new ModelAndView();
        lista.setViewName("/aluno/alunos-cancelados");
        lista.addObject("alunosCancelados", alunoRepository.findByStatusCancelado());
        lista.addObject("aluno", new Aluno());
        return lista;
    }

    @GetMapping("/alunos-trancados")
    public ModelAndView listaAlunosTrancados(){
        ModelAndView lista = new ModelAndView();
        lista.setViewName("/aluno/alunos-trancados");
        lista.addObject("alunosTrancados", alunoRepository.findByStatusTrancado());
        lista.addObject("aluno", new Aluno());
        return lista;
    }

    @PostMapping("/pesquisar-aluno")
    public ModelAndView pesquisar( @RequestParam(required = false) String nome){
        ModelAndView pesquisa = new ModelAndView();
        pesquisa.addObject("aluno", new Aluno());
        List<Aluno> listaAlunos;

        if(nome == null || nome.trim().isEmpty()){
            listaAlunos = alunoRepository.findAll();
        } else {
            listaAlunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        }
        pesquisa.addObject("listaAlunos",listaAlunos);
        pesquisa.setViewName("/aluno/pesquisa-resultado");
        return pesquisa;
    }
}
