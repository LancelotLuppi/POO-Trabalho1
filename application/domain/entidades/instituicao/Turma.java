package application.domain.entidades.instituicao;

import application.domain.entidades.Entity;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.Arrays;
import java.util.Objects;

public class Turma extends Entity {

    private Professor professor;
    private Aluno[] alunos;

    public Turma(int codigo, String disciplina, Professor professor) {
        super.codigo = codigo;
        super.nome = disciplina;
        this.professor = professor;
        this.alunos = new Aluno[30];
    }

    public Turma(int codigo, String disciplina, Professor professor, Aluno[] alunos) {
        super.codigo = codigo;
        super.nome = disciplina;
        this.professor = professor;
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno[] getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }

    public long getAlunosSize() {
        return Arrays.stream(alunos).filter(Objects::nonNull).count();
    }

    public int obterQuantidadeAprovados() {
        return (int) Arrays.stream(alunos).filter(Objects::nonNull).filter(Aluno::estaAprovado).count();
    }

    public double getPercentualAprovados() {
        int alunos = (int) getAlunosSize();
        int aprovados = obterQuantidadeAprovados();
        return alunos > 0 ? (double) aprovados / alunos * 100 : 0;
    }

    public void printDadosDetalhados() {
        printDadosBasicos();
        System.out.println("----- Alunos -----");
        Arrays.stream(alunos).filter(Objects::nonNull).forEach(aluno -> {
            System.out.println("Código do Aluno: " + aluno.getCodigo());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println(
                    "N1: " + String.format("%.2f", aluno.getNotaByIndex(0))
                    + " | N2: " + String.format("%.2f", aluno.getNotaByIndex(1))
                    + " | N3: " + String.format("%.2f", aluno.getNotaByIndex(2))
            );
            System.out.println("Media: " + String.format("%.2f", aluno.calcularMedia()));
            System.out.println("Situação: " + (aluno.estaAprovado() ? "Aprovado" : "Reprovado"));
            System.out.println("---------------");
        });
    }

    public void printDadosBasicos() {
        System.out.println("----- Dados da Turma -----");
        System.out.println("Código: " + super.getCodigo());
        System.out.println("Disciplina: " + super.getNome());
        System.out.println("Professor: " + getProfessor().getNome());
        System.out.println("Quantidade de Alunos: " + getAlunosSize());
    }

    public void printDadosEstatistica() {
        printDadosBasicos();
        System.out.println("Quantidade aprovados: " + obterQuantidadeAprovados());
        System.out.println("Percentual aprovacao: " + String.format("%.2f", getPercentualAprovados()) + "%");
        System.out.println();
    }
}
