package models;

import java.util.ArrayList;

public class Aluno {
  private Integer idAluno;
  private String nome;
  private ArrayList<Nota> notas;

  public Integer getIdAluno() {
    return this.idAluno;
  }

  public void setIdAluno(Integer idAluno) {
    this.idAluno = idAluno;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ArrayList<Nota> getNotas() {
    return this.notas;
  }

  public void setNotas(ArrayList<Nota> notas) {
    this.notas = notas;
  }

}
