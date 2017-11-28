package service;

import java.util.List;
import modelo.Aluno;
import modelo.Disciplina;
import modelo.MatrizDisciplina;
import fabricas.Fabrica;
import modelo.Curso;
import modelo.MatrizCurricular;

public class ServiceFacadeImpl implements ServiceFacade {

    private SessionService loginService;
    private CursoService cursoService;
    private DisciplinaService disciplinaService;
    private SimulacaoService simulacaoService;
    private RequisitosService requisitosService;
    
    public ServiceFacadeImpl() {
        loginService = SessionService.getInstance();
        cursoService = new CursoService();
        disciplinaService = new DisciplinaService();
        simulacaoService = SimulacaoService.getInstance();
        requisitosService = Fabrica.getInstance().getFactory().createRequisitosService();
    }

    @Override
    public Double coletarMediaAprovacao(Disciplina disciplina){
        return disciplinaService.coletarMediaAprovacao(disciplina);
    }

    @Override
    public Aluno coletarAlunoLogado() {
        return loginService.getAluno();
    }

    @Override
    public List<MatrizDisciplina> carregarDisciplinasDisponiveis(Curso curso, MatrizCurricular matriz) {
        return cursoService.coletarDisciplinasDisponiveis(coletarAlunoLogado(), matriz);
    }

    @Override
    public String coletarRecomendacaoSemestre(Aluno aluno, List<MatrizDisciplina> disciplinas) {
        return simulacaoService.coletarRecomendacaoSemestre(aluno,disciplinas);
    }

    @Override
    public List<Disciplina> coletarDisciplinasDoCurso(Curso curso) {
        return cursoService.coletarDisciplinasDoCurso(curso);
    }

    @Override
    public boolean cumpreCoRequisitos(Aluno aluno, MatrizDisciplina disciplinaAAdicionar, List<MatrizDisciplina> disciplinasM) {
        return requisitosService.cumpreCoRequisitos(aluno, disciplinaAAdicionar, disciplinasM);
    }

    @Override
    public void ordenarDisciplinas(List<MatrizDisciplina> disciplinas) {
        disciplinaService.ordenarDisciplinas(disciplinas);
    }
}
