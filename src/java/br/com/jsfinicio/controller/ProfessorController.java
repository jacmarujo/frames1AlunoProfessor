/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsfinicio.controller;

import br.com.jsfinicio.model.AreaModel;
import br.com.jsfinicio.model.CidadeModel;
import br.com.jsfinicio.model.EstadoModel;
import br.com.jsfinicio.model.ProfessorModel;
import br.com.jsfinicio.repository.AreaRepository;
import br.com.jsfinicio.repository.CidadeRepository;
import br.com.jsfinicio.repository.EstadoRepository;
import br.com.jsfinicio.repository.ProfessorRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jacson
 */
@ManagedBean
@SessionScoped
public class ProfessorController {

    private ProfessorModel professorModel;
    private EstadoModel estadoModel;
    private AreaModel areaModel;
    private CidadeModel cidadeModel;
    private EstadoRepository estadoRepository;
    private AreaRepository areaRepository;
    private CidadeRepository cidadeRepository;
    private ProfessorRepository professorRepository;
    private List<ProfessorModel> listaDeProfessores;

    public ProfessorController() {
        this.estadoRepository = new EstadoRepository();
        this.areaRepository = new AreaRepository();
        this.cidadeRepository = new CidadeRepository();
        this.professorModel = new ProfessorModel();
        this.estadoModel = new EstadoModel();
        this.areaModel = new AreaModel();
        this.cidadeModel = new CidadeModel();
        this.professorRepository = new ProfessorRepository();
        this.listaDeProfessores = new ArrayList<>();
    }

    public void salvar() {
        try {
            this.estadoModel = this.estadoRepository.buscarPorID(this.estadoModel.getIdEstado());
            this.areaModel = this.areaRepository.buscarPorId(this.areaModel.getIdArea());
            this.cidadeModel = this.cidadeRepository.buscarPorID(this.cidadeModel.getIdCidade());
            this.professorModel.setEstadoOrigem(this.estadoModel);
            this.professorModel.setCidadeOrigem(this.cidadeModel);
            this.professorRepository.salvar(this.professorModel);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("", "Operação realizada com sucesso!"));
            this.professorModel = new ProfessorModel();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("", "Cadastro não realizado!"
                    + ", " + cidadeModel.getNome() +professorModel.getSenha()));
        }
    }

    public void buscar() {
        this.listaDeProfessores = this.professorRepository.buscarTodos();
    }
    
    public void buscarPorNome() {
        this.listaDeProfessores = this.professorRepository.buscarPorNome(this.professorModel.getNome());
    }
    
    public void excluirPorID(long idpessoa) {
        this.professorRepository.excluirPorID(idpessoa);
    }

    public String editarPorID(long idpessoa) throws IOException {
        this.professorModel = this.professorRepository.buscarPorID(idpessoa);

        return "editarProfessor.xhtml?faces-redirect=true";
    }

//    public void popularPorEstado(){
//        try{
//            this.cidadeRepository = new CidadeRepository();
//            List<CidadeModel> cidades = cidadeRepository.buscarCidadePorEstado(estadoModel);
//        }catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage("", new FacesMessage( "", "Estado não encontrado!"
//                     +", "+professorModel.getNome()  +", "+professorModel +", "+professorModel.getCidadeOrigem().getNome()
//                    +professorModel.getEstadoOrigem().getNome() +estadoModel.getNome() + professorModel.getSiape() +cidadeModel.getNome()));
//        }
//    }
//    public void buscarPorNome() {
//        this.listaDeAlunos = this.alunoRepository.buscarPorNome(this.alunoModel.getNome());
//    }
   
    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public ProfessorRepository getProfessorRepository() {
        return professorRepository;
    }

    public void setProfessorRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorModel> getListaDeProfessores() {
        return listaDeProfessores;
    }

    public void setListaDeProfessores(List<ProfessorModel> listaDeProfessor) {
        this.listaDeProfessores = listaDeProfessor;
    }

    public AreaModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(AreaModel areaModel) {
        this.areaModel = areaModel;
    }

    public AreaRepository getAreaRepository() {
        return areaRepository;
    }

    public void setAreaRepository(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public EstadoModel getEstadoModel() {
        return estadoModel;
    }

    public void setEstadoModel(EstadoModel estadoModel) {
        this.estadoModel = estadoModel;
    }

    public EstadoRepository getEstadoRepository() {
        return estadoRepository;
    }

    public void setEstadoRepository(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public CidadeModel getCidadeModel() {
        return cidadeModel;
    }

    public void setCidadeModel(CidadeModel cidadeModel) {
        this.cidadeModel = cidadeModel;
    }

    public CidadeRepository getCidadeRepository() {
        return cidadeRepository;
    }

    public void setCidadeRepository(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

}
