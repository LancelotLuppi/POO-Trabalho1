package application.domain.entidades.usuario;

public class Professor extends Usuario {

    private String universidadeFormacao;

    public Professor(int codigo, String nome, String email, String universidade) {
        super.codigo = codigo;
        super.nome = nome;
        super.email = email;
        this.universidadeFormacao = universidade;
    }

    public String getUniversidadeFormacao() {
        return universidadeFormacao;
    }

    public void setUniversidadeFormacao(String universidadeFormacao) {
        this.universidadeFormacao = universidadeFormacao;
    }
}
