package application.domain.entidades.instituicao;

import application.domain.entidades.Entity;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.ArrayList;
import java.util.List;

public class Turma extends Entity {

    private Professor professor;
    private List<Aluno> alunos;

    public Turma(int codigo, String disciplina, Professor professor) {
        super.codigo = codigo;
        super.nome = disciplina;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        if (alunos.size() < 30) {
            alunos.add(aluno);
        } else {
            System.out.println("Limite de alunos atingido para esta turma.");
        }
    }

    public int obterQuantidadeAlunos() {
        return alunos.size();
    }

    public int obterQuantidadeAprovados() {
        int count = 0;
        for (Aluno aluno : alunos) {
            if (aluno.estaAprovado()) {
                count++;
            }
        }
        return count;
    }

    public void mostrarDados() {
        System.out.println("----- Dados da Turma -----");
        System.out.println("Código: " + super.getCodigo());
        System.out.println("Disciplina: " + super.getNome());
        System.out.println("Professor: " + getProfessor().getNome());
        System.out.println("Quantidade de Alunos: " + obterQuantidadeAlunos());
        System.out.println("----- Alunos -----");
        for (Aluno aluno : alunos) {
            System.out.println("Código do Aluno: " + aluno.getCodigo());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Notas: " + aluno.calcularMedia());
            System.out.println("Situação: " + (aluno.estaAprovado() ? "Aprovado" : "Reprovado"));
            System.out.println("-----");
        }
    }
}
