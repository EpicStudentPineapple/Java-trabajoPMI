package controller;

import view.VistaLogin;
import view.VistaProfesor;

import java.util.List;

import modelo.SeguimientoAlumno;
import view.VistaCursoProfesor;
import view.VistaSeguimientoAlumno;

public class ControllerProfesor {
    private VistaProfesor vp;
    private VistaLogin vl;
    private String nombre;
    
    public ControllerProfesor(VistaProfesor vp, VistaLogin vl, String nombre) {
        this.vp = vp;
        this.vl = vl;
        this.nombre = nombre;
        
        // Boton cursos
        this.vp.getBtnCursos().addActionListener(e -> {
            vp.cerrar();
            VistaCursoProfesor vcp = new VistaCursoProfesor();
            ControllerCursoProfesor ccp = new ControllerCursoProfesor(vcp, vp);
            ccp.iniciar();
        });
        
        // Boton seguimiento alumno
        this.vp.getBtnSeguimientoAlumno().addActionListener(e -> {
            vp.cerrar();
            VistaSeguimientoAlumno vistaSeguimiento = new VistaSeguimientoAlumno();
            ControllerSeguimientoAlumno csa = new ControllerSeguimientoAlumno(vistaSeguimiento, vp);
            csa.iniciar();
        });
        
        // Boton cerrar sesion
        this.vp.getBtnCerrarSesion().addActionListener(e ->{
        	vp.cerrar();
        	VistaLogin vistaLogin = new VistaLogin();
        	ControllerLogin controllerLogin = new ControllerLogin(vistaLogin);
        	controllerLogin.iniciar();
        });
    }
    
    public void iniciar() {
        vp.iniciar();
    }
}