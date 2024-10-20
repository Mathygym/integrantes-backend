package pe.edu.cibertec.integrantes_backend.service;

import pe.edu.cibertec.integrantes_backend.dto.LoginResquestDTO;

import java.io.IOException;

public interface AutenticacionService {

    String[] validarAlumno(LoginResquestDTO loginResquestDTO) throws IOException;
}
