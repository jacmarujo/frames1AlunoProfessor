/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsfinicio.controller;

import br.com.jsfinicio.model.AreaModel;
import br.com.jsfinicio.model.DisciplinaModel;
import br.com.jsfinicio.model.ProfessorModel;
import br.com.jsfinicio.repository.AreaRepository;
import br.com.jsfinicio.repository.DisciplinaRepository;
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
 * @author jojo
 */
@ManagedBean
@SessionScoped
public class DisciplinaController {

    private DisciplinaModel disciplinaModel;
    private ProfessorModel professorModel;
    private AreaModel areaModel;
    private DisciplinaRepository disciplinaRepository;
    private AreaRepository areaRepository;
    private ProfessorRepository professorRepository;
    private List<DisciplinaModel> listaDeDisciplina;
    
    public DisciplinaController() {
        this.disciplinaModel = new DisciplinaModel();
        this.professorModel = new ProfessorModel();
        this.areaModel = new AreaModel();
        this.professorRepository = new ProfessorRepository();
        this.areaRepository = new AreaRepository();
        this.disciplinaRepository = new DisciplinaRepository();
        this.listaDeDisciplina = new ArrayList<>();
    }

    public void salvar() {
        try {
            this.areaModel = this.areaRepository.buscarPorId(this.areaModel.getIdArea());
            this.professorModel = this.professorRepository.buscarPorID(this.professorModel.getIdpessoa());
            this.disciplinaModel.setArea(this.areaModel);
            this.disciplinaModel.setProfessor(this.professorModel);
            this.disciplinaRepository.salvar(this.disciplinaModel);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("", "Operação realizada com sucesso!"));
            this.disciplinaModel = new DisciplinaModel();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("", "Cadastro não realizado!" +" - " +disciplinaModel.getNome()
            +" - " +disciplinaModel.getCargaHoraria() +" - " +disciplinaModel.getDescricao()));
        }
    }

    public void buscar() {
        this.listaDeDisciplina = this.disciplinaRepository.buscarTodos();
    }

    public void buscarPorNome() {
        this.listaDeDisciplina = this.disciplinaRepository.buscarPorNome(this.disciplinaModel.getNome());
    }
    
    public void excluirPorID(int idDisciplina) {
        this.disciplinaRepository.excluirPorID(idDisciplina);
    }

    public String editarPorID(int idDisciplina) throws IOException {
        this.disciplinaModel = this.disciplinaRepository.buscarPorId(idDisciplina);

        return "editarDisciplina.xhtml?faces-redirect=true";
    }
    
    public DisciplinaModel getDisciplinaModel() {
        return disciplinaModel;
    }

    public void setDisciplinaModel(DisciplinaModel disciplinaModel) {
        this.disciplinaModel = disciplinaModel;
    }

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public AreaModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(AreaModel areaModel) {
        this.areaModel = areaModel;
    }

    public DisciplinaRepository getDisciplinaRepository() {
        return disciplinaRepository;
    }

    public void setDisciplinaRepository(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public AreaRepository getAreaRepository() {
        return areaRepository;
    }

    public void setAreaRepository(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public ProfessorRepository getProfessorRepository() {
        return professorRepository;
    }

    public void setProfessorRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<DisciplinaModel> getListaDeDisciplina() {
        return listaDeDisciplina;
    }

    public void setListaDeDisciplina(List<DisciplinaModel> listaDeDisciplina) {
        this.listaDeDisciplina = listaDeDisciplina;
    }
    
    
}
