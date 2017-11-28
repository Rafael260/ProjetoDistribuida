package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Serializable{

    private Integer id;
    
    private String nome;
    //Bacharelado, licenciatura, etc
    private String grauAcademico;
    //Presencial, EAD, etc
    private String modalidade;
    
    private List<MatrizCurricular> matrizesCurricular;
    
    private List<Aluno> alunos;

    public Curso(){
//        this.matrizesCurricular = new ArrayList<>();
//        this.alunos = new ArrayList<>();
    }
    
    public Curso(String nome) {
        this.nome = nome;
        this.matrizesCurricular = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrauAcademico() {
        return grauAcademico;
    }

    public void setGrauAcademico(String grauAcademico) {
        this.grauAcademico = grauAcademico;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
    
    public List<MatrizCurricular> getMatrizesCurricular() {
        return matrizesCurricular;
    }

    public void setMatrizesCurricular(List<MatrizCurricular> matrizesCurricular) {
        this.matrizesCurricular = matrizesCurricular;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public MatrizDisciplina coletarMatrizDisciplina(String nomeMatriz, String codigoDisciplina){
        MatrizCurricular matriz = coletarMatrizCurricular(nomeMatriz);
        return matriz.getDisciplina(codigoDisciplina);
    }
    
    public MatrizCurricular coletarMatrizCurricular(String nomeMatriz){
        for(MatrizCurricular m: matrizesCurricular){
            if (m.getNomeMatriz().equalsIgnoreCase(nomeMatriz)){
                return m;
            }
        }
        return null;
    }
    
    public Disciplina coletarDisciplina(String codigoDisciplina){
        for (Disciplina disc: coletarDisciplinas()){
            if (disc.getCodigo().equals(codigoDisciplina)){
                return disc;
            }
        }
        return null;
    }

    public List<Disciplina> coletarDisciplinas(){
        List<Disciplina> disciplinas = new ArrayList<>();
        for (MatrizCurricular matriz: matrizesCurricular){
            List<MatrizDisciplina> disciplinasNaMatriz = matriz.getDisciplinasNaMatriz();
            for (MatrizDisciplina matrizDisciplina: disciplinasNaMatriz){
                if (!disciplinas.contains(matrizDisciplina.getDisciplina())){
                    disciplinas.add(matrizDisciplina.getDisciplina());
                }
            }
        }
        return disciplinas;
    }
}
