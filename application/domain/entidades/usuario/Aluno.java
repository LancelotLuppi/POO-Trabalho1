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

    public Aluno(int codigo, String nome, String email, Curso curso, double[] notas) {
        super.codigo = codigo;
        super.nome = nome;
        super.email = email;
        this.curso = curso;
        this.notas = notas;
    }

    public double calcularMedia() {
        double sum = 0;
        for (double nota : notas) {
            sum += nota;
        }
        return sum / notas.length;
    }

    public boolean estaAprovado() {
        return calcularMedia() >= 7.0;
    }
    public double getNotaByIndex(int index) {
        if(index >= 0 && index <= 3)
            return this.notas[index];
        return 0;
    }
}
