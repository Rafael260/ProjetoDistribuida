package modelo;

import java.io.Serializable;
import java.util.List;

public class Turma implements Serializable{

    private Integer id;
    
    private String periodoLetivo;
    private String codigoTurma;
    
    private Disciplina disciplina;
    
    private List<Matricula> matriculas;
    
    
    public Turma(){
//        this.matriculas = new ArrayList<>();
        this.codigoTurma = "T01";
    }

    public Turma(String periodoLetivo, Disciplina disciplina) {
        this.periodoLetivo = periodoLetivo;
        this.disciplina = disciplina;
//        this.matriculas = new ArrayList<>();
        this.codigoTurma = "T01";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(String periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }
    
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public String toString(){
        return this.disciplina.getCodigo() + " - " + this.periodoLetivo + " - " + this.codigoTurma;
    }
}
