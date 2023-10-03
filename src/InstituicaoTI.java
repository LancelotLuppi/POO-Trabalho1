import java.util.ArrayList;
import java.util.List;

public class InstituicaoTI {
    private List<Turma> turmas;

    public InstituicaoTI() {
        this.turmas = new ArrayList<>();
    }

    public void adicionarTurma(Turma turma) {
        turmas.add(turma);
    }

    public Turma[] getTurmas() {
        return turmas.toArray(new Turma[0]);
    }

    public int getNumTurmas() {
        return turmas.size();
    }

    public void listarTodasTurmas() {
        System.out.println("----- Lista de Turmas -----");
        for (Turma turma : turmas) {
            System.out.println("Código: " + turma.getCodigo());
            System.out.println("Disciplina: " + turma.getDisciplina());
            System.out.println("Professor: " + turma.getProfessor().getNome());
            System.out.println("Quantidade de Alunos: " + turma.obterQuantidadeAlunos());
            System.out.println("-----");
        }
    }

    public void mostrarEstatisticasGerais() {
        int totalAlunos = 0;
        int totalAprovados = 0;

        for (Turma turma : turmas) {
            totalAlunos += turma.obterQuantidadeAlunos();
            totalAprovados += turma.obterQuantidadeAprovados();
        }

        double percentualAprovacaoTotal = totalAlunos > 0 ? (double) totalAprovados / totalAlunos * 100 : 0;

        System.out.println("----- Estatísticas Gerais -----");
        System.out.println("Total de Turmas: " + getNumTurmas());
        System.out.println("Total de Alunos Matriculados: " + totalAlunos);
        System.out.println("Total de Alunos Aprovados: " + totalAprovados);
        System.out.println("Percentual de Aprovação Geral: " + percentualAprovacaoTotal + "%");
        System.out.println("-----");
    }
}