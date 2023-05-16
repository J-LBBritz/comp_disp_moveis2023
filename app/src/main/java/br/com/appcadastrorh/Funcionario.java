package br.com.appcadastrorh;

public class Funcionario {

    private int matricula;
    private String nome;
    private String email;
    private String dataNasc;
    private Area area;

    public Funcionario() {
    }

    public Funcionario(String nome, String email, String dataNasc, Area area) {
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.area = area;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return nome + " \n " +
                email + " \n " +
                dataNasc + " \n " +
                area;
    }
}
