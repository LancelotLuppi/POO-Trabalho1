package application.domain.entidades.instituicao;

import application.domain.entidades.Entity;

public class Curso extends Entity {
    public Curso(int codigo, String nome) {
        super.codigo = codigo;
        super.nome = nome;
    }
}
