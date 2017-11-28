package modelo;

import java.io.Serializable;
import java.util.List;

public class MatrizCurricular implements Serializable {

    private Integer id;
    
    private String nomeMatriz;
    
    private Curso curso;
    
    private List<MatrizDisciplina> disciplinasNaMatriz;

    public MatrizCurricular() {
    }
    
    public MatrizCurricular(String nomeMatriz) {
        this.nomeMatriz = nomeMatriz;
//        this.disciplinasNaMatriz = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeMatriz() {
        return nomeMatriz;
    }

    public void setNomeMatriz(String nomeMatriz) {
        this.nomeMatriz = nomeMatriz;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<MatrizDisciplina> getDisciplinasNaMatriz() {
        return disciplinasNaMatriz;
    }

    public void setDisciplinasNaMatriz(List<MatrizDisciplina> disciplinasNaMatriz) {
        this.disciplinasNaMatriz = disciplinasNaMatriz;
    }

    public MatrizDisciplina getDisciplina(String codigo){
        for(MatrizDisciplina matrizDisciplina: this.disciplinasNaMatriz){
            if(matrizDisciplina.getDisciplina().getCodigo().equals(codigo)){
                return matrizDisciplina;
            }
        }
        return null;
    }
    
    public String toString(){
        return this.id + " " + this.nomeMatriz;
    }
}
