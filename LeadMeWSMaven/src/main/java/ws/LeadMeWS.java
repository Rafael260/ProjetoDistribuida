/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import base_dados.CursoDAO;
import base_dados.DisciplinaDAO;
import base_dados.TurmaDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import excecoes.AutenticacaoException;
import excecoes.DataException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.Curso;
import modelo.Disciplina;
import modelo.MatrizCurricular;
import modelo.Turma;
import service.DisciplinaService;

/**
 * REST Web Service
 *
 * @author rafao
 */
@Path("leadme")
public class LeadMeWS {

    @Context
    private UriInfo context;
    
    private CursoDAO cursoDAO;
    private DisciplinaDAO disciplinaDAO;
    private TurmaDAO turmaDAO;
    private DisciplinaService disciplinaService;
    
    Gson gson;
    /**
     * Creates a new instance of LeadMeWS
     */
    public LeadMeWS() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        cursoDAO = CursoDAO.getInstance();
        disciplinaDAO = DisciplinaDAO.getInstance();
        turmaDAO = TurmaDAO.getInstance();
        disciplinaService = new DisciplinaService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("disciplinas")
    public String getDisciplinasDoCurso(@QueryParam("curso") Integer idCurso) throws DataException, AutenticacaoException{
        Curso curso = cursoDAO.encontrar(idCurso);
        List<Disciplina> disciplinas = curso.coletarDisciplinas();
        return gson.toJson(disciplinas);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cursos/{id}")
    public String getCurso(@PathParam("id") Integer id){
        Curso curso = cursoDAO.encontrar(id);
        return gson.toJson(curso);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("matrizescurriculares")
    public String getMatrizesCurriculares(@QueryParam("idCurso")Integer idCurso){
        Curso curso = cursoDAO.encontrar(idCurso);
        List<MatrizCurricular> matrizesCurriculares = curso.getMatrizesCurricular();
        return gson.toJson(matrizesCurriculares);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("turmas")
    public String getTurmas(@QueryParam("idComponente")Integer idComponente){ //55022
        Disciplina disciplina = disciplinaDAO.encontrar(idComponente);
        return gson.toJson(disciplina.getTurmas());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("turmas/{id}")
    public String getTurma(@PathParam("id")Integer id){ //
        Turma turma = turmaDAO.encontrar(id);
        return gson.toJson(turma);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("disciplina")
    public String getDisciplina(@QueryParam("idTurma") Integer idTurma){
        Turma turma = turmaDAO.encontrar(idTurma);
        Disciplina disciplina = turma.getDisciplina();
        System.out.println("Disciplina da turma de id: " + idTurma + ": " + (disciplina != null ? disciplina.getNome() : "null"));
        return gson.toJson(disciplina);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("disciplina/mediaAprovacao")
    public Double getMediaAprovacao(@QueryParam("idComponente") Integer idDisciplina){
        Disciplina disciplina = disciplinaDAO.encontrar(idDisciplina);
        return disciplinaService.coletarMediaAprovacao(disciplina);
    }
}
