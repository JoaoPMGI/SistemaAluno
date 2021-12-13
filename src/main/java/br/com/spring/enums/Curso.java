package br.com.spring.enums;

public enum Curso {
    CC("Ciência da Computação"),
    TADS("Tecnologia em Análise e Desenvolvimento de Sistemas"),
    SI("Sistemas da Informação"),
    REDES("Tecnólogo em Redes");

    private String curso;

    private Curso(String curso){
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }
}
