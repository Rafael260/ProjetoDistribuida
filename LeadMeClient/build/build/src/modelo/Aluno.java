package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aluno implements Serializable{

    private String id;
    
    private String nome;
    
    private Curso curso;
    
    private List<Matricula> matriculas;
    
    public Aluno() {
        curso = null;
//        matriculas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    //TODO remover metodo e utilizar no service
    public Map<String,List<Disciplina>> coletarMatriculasAgrupadasPorPeriodoLetivo(boolean apenasAprovados){
        Map<String,List<Disciplina>> disciplinasAgrupadas = new HashMap<>();
        List<Disciplina> disciplinasDoPeriodo;
        String periodoLetivo;
        for (Matricula matricula: matriculas){
            if (apenasAprovados && !matricula.situacaoAprovada()){
                continue;
            }
            periodoLetivo = matricula.getTurma().getPeriodoLetivo();
            if (!disciplinasAgrupadas.containsKey(periodoLetivo)){
                disciplinasAgrupadas.put(periodoLetivo, new ArrayList<Disciplina>());
            }
            disciplinasDoPeriodo = disciplinasAgrupadas.get(periodoLetivo);
            disciplinasDoPeriodo.add(matricula.getTurma().getDisciplina());
            disciplinasAgrupadas.put(periodoLetivo, disciplinasDoPeriodo);
        }
        return disciplinasAgrupadas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        return this.id.equals(other.getId());
    }
    
    

}
