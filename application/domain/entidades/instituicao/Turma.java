package application.domain.entidades.instituicao;

import application.domain.entidades.Entity;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Turma extends Entity {

    private Professor professor;
    private Aluno[] alunos;

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

    public Optional<Aluno> consultaAlunoPorCodigo(int codigo) {
        return Arrays.stream(alunos).filter(Objects::nonNull).filter(aluno -> aluno.getCodigo().equals(codigo)).findFirst();
    }

    public double getPercentualAprovados() {
        int alunos = (int) getAlunosSize();
        int aprovados = obterQuantidadeAprovados();
        return alunos > 0 ? (double) aprovados / alunos * 100 : 0;
    }

    public void printDadosBasicos() {
        System.out.println("----- Dados da Turma -----");
        System.out.println("Código: " + super.getCodigo());
        System.out.println("Disciplina: " + super.getNome());
        System.out.println("Professor: " + getProfessor().getNome());
        System.out.println("Quantidade de Alunos: " + getAlunosSize());
    }

    public void printDadosDetalhados() {
        printDadosBasicos();
        System.out.println("----- Alunos -----");
        Arrays.stream(alunos).filter(Objects::nonNull).forEach(Aluno::printDadosDetalhados);
    }

    public void printDadosEstatistica() {
        printDadosBasicos();
        System.out.println("Quantidade aprovados: " + obterQuantidadeAprovados());
        System.out.println("Percentual aprovacao: " + String.format("%.2f", getPercentualAprovados()) + "%");
        System.out.println();
    }
}
