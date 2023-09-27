package domain.pessoa;

public class Professor extends Pessoa {

    private String universidadeFormacao;
    private String disciplina;

    public String getUniversidadeFormacao() {
        return universidadeFormacao;
    }

    public void setUniversidadeFormacao(String universidadeFormacao) {
        this.universidadeFormacao = universidadeFormacao;
    }
}
