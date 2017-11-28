package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable{

    private Integer id;
    
    private String codigo;
    private String nome;
    private Integer cargaHoraria;
    
    private String preRequisitos;
    
    private String equivalencias;
    
    private String coRequisitos;
    
    private List<MatrizDisciplina> matrizesRelacionadas;

    List<Turma> turmas;
    
    private Double mediaAprovacao;
    
    public Disciplina(){
//        turmas = new ArrayList<>();
//        matrizesRelacionadas = new ArrayList<>();
    	mediaAprovacao = null;
    }

    public Disciplina(String codigo, String nome, Integer cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        turmas = new ArrayList<>();
        matrizesRelacionadas = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<MatrizDisciplina> getMatrizesRelacionadas() {
        return matrizesRelacionadas;
    }

    public void setMatrizesRelacionadas(List<MatrizDisciplina> matrizesRelacionadas) {
        this.matrizesRelacionadas = matrizesRelacionadas;
    }

    public String getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(String preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public String getEquivalencias() {
        return equivalencias;
    }

    public void setEquivalencias(String equivalencias) {
        this.equivalencias = equivalencias;
    }

    public String getCoRequisitos() {
        return coRequisitos;
    }

    public void setCoRequisitos(String coRequisitos) {
        this.coRequisitos = coRequisitos;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Curso getCurso() {
        return this.matrizesRelacionadas.get(0).getMatrizCurricular().getCurso();
    }
    
    public Double getMediaAprovacao() {
		return mediaAprovacao;
	}

	public void setMediaAprovacao(Double mediaAprovacao) {
		this.mediaAprovacao = mediaAprovacao;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Disciplina other = (Disciplina) obj;
        return this.codigo.equals(other.getCodigo());
    }

    @Override
    public String toString() {
        return nome + " - " + codigo;
    }
}
