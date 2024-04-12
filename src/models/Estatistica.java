package models;

public class Estatistica {
  private Float media;
  private Float max;
  private Float min;
  private Float var;

  public Float getMedia() {
    return this.media;
  }

  public void setMedia(Float media) {
    this.media = media;
  }

  public Float getMax() {
    return this.max;
  }

  public void setMax(Float max) {
    this.max = max;
  }

  public Float getMin() {
    return this.min;
  }

  public void setMin(Float min) {
    this.min = min;
  }

  public Float getVar() {
    return this.var;
  }

  public void setVar(Float var) {
    this.var = var;
  }

  @Override
  public String toString() {
    return "Estatistica [media=" + media + ", max=" + max + ", min=" + min + ", var=" + var + "]";
  }



}
