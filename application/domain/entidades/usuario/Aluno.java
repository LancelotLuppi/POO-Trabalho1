package application.domain.entidades.usuario;

import application.domain.entidades.instituicao.Curso;

public class Aluno extends Usuario {

    private double[] notas;
    private Curso curso;

    public Aluno(int codigo, String nome, String email, Curso curso) {
        super.codigo = codigo;
        super.nome = nome;
        super.email = email;
        this.curso = curso;
        this.notas = new double[3];
    }

    public Aluno(int codigo, String nome, String email, double[] notas) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.notas = notas;
    }

    public double[] getNotas() {
        return notas;
    }

    public void adicionarNota(int indice, double nota) {
        if (indice >= 0 && indice < notas.length) {
            notas[indice] = nota;
        }
    }

    public double calcularMedia() {
        double sum = 0;
        for (double nota : notas) {
            sum += nota;
        }
        return sum / notas.length;
    }

    public boolean estaAprovado() {
        return calcularMedia() >= 6.0;
    }
}
