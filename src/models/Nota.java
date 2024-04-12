package models;

public class Nota {
  private Integer idNota;
  private Integer nota;
  private Aluno aluno;

  public Integer getIdNota() {
    return this.idNota;
  }

  public void setIdNota(Integer idNota) {
    this.idNota = idNota;
  }

  public Integer getNota() {
    return this.nota;
  }

  public void setNota(Integer nota) {
    this.nota = nota;
  }

  public Aluno getAluno() {
    return this.aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }
}
