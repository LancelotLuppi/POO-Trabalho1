import java.util.ArrayList;
import java.util.List;

public class Turma {
    private int codigo;
    private String disciplina;
    private Professor professor;
    private List<Aluno> alunos;

    public Turma(int codigo, String disciplina, Professor professor) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
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

    public double obterPercentualAprovacao() {
        if (alunos.isEmpty()) {
            return 0.0;
        }
        return ((double) obterQuantidadeAprovados() / alunos.size()) * 100.0;
    }

    public void mostrarDados() {
        System.out.println("----- Dados da Turma -----");
        System.out.println("Código: " + getCodigo());
        System.out.println("Disciplina: " + getDisciplina());
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
