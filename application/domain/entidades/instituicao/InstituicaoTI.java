package application.domain.entidades.instituicao;

import application.domain.entidades.usuario.Professor;

import java.util.Arrays;
import java.util.Objects;

public class InstituicaoTI {
    private Turma[] turmas = new Turma[30];

    private final Curso[] cursos = new Curso[4];
    private final Professor[] professores = new Professor[30];

    public InstituicaoTI() {
        cursos[0] = new Curso(1, "Ciência da Computação");
        cursos[1] = new Curso(2, "Engenharia de Software");
        cursos[2] = new Curso(3, "Engenharia de Computação");
        cursos[3] = new Curso(4, "Análise e Desenvolvimento de Sistemas");
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public Curso getCursoByIndex(int index) {
        if(index >= 0 && index < 4) {
            return cursos[index];
        }
        return null;
    }

    public boolean isCodigoExistenteProfessor(int codigo) {
        return Arrays.stream(professores).anyMatch(professor -> professor.getCodigo().equals(codigo));
    }
    public boolean isNomeExistenteProfessor(String nome) {
        return Arrays.stream(professores).anyMatch(professor -> professor.getNome().equalsIgnoreCase(nome));
    }

    public void adicionarTurma(Turma turma, int index) {
        if (index >= 0 && index < 30)
            turmas[index] = turma;
        else
            System.out.println("Maximo de turmas alcançado (30)");
    }

    public Turma[] getTurmas() {
        return turmas;
    }

    public long getNumTurmas() {
        return Arrays.stream(turmas).filter(Objects::nonNull).count();
    }

    public void listarTodasTurmas() {
        System.out.println("----- Lista de Turmas -----");
        Arrays.stream(turmas).forEach(Turma::printDadosBasicos);
    }

    public void mostrarEstatisticasGerais() {
        int totalAlunos = 0;
        int totalAprovados = 0;

        for (Turma turma : turmas) {
            totalAlunos += turma.getAlunosSize();
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
