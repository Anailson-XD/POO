package br.com.ifce.academico;

import java.util.ArrayList;
import java.util.List;

public class Diario {
    private int codigo;
    private String professor;
    List<Aluno> alunos = new ArrayList<>();
    private CalcularMedia calculadora;

    public void addAluno(Aluno aluno){
        addAluno(aluno);
    }

    public void CalculaMedia(Disciplina d){
        double m = calculadora.calc(d.getN1(), d.getN2());
        d.setMedia(m);
        d.setAprovacao(m>=6);
    }

    public CalcularMedia getCalculadora() {
        return calculadora;
    }

    public void setCalculadora(CalcularMedia calculadora) {
        this.calculadora = calculadora;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
