package modelo;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Turma implements Serializable{

    @Expose
    @Id
    private Integer id;
    
    @Expose
    private String periodoLetivo;
    
    @Expose
    private String codigoTurma;
    
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    
    @Expose
    @OneToMany(mappedBy = "turma")
    private List<Matricula> matriculas;
    
    
    public Turma(){
        this.matriculas = new ArrayList<>();
        this.codigoTurma = "T01";
    }

    public Turma(String periodoLetivo, Disciplina disciplina) {
        this.periodoLetivo = periodoLetivo;
        this.disciplina = disciplina;
        this.matriculas = new ArrayList<>();
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

    //Utiliza o lock para adicionar a matricula na lista
    public Matricula adicionarAluno(Aluno aluno){
        Matricula novaMatricula = new Matricula(this,aluno);
        this.matriculas.add(novaMatricula);
        aluno.adicionarMatricula(novaMatricula);
        return novaMatricula;
    }
    
    public String toString(){
        return this.disciplina.getCodigo() + " - " + this.periodoLetivo + " - " + this.codigoTurma;
    }
}
