package pe.edu.cibertec.integrantes_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.integrantes_backend.dto.LoginResponseDTO;
import pe.edu.cibertec.integrantes_backend.dto.LoginResquestDTO;
import pe.edu.cibertec.integrantes_backend.service.AutenticacionService;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginResquestDTO loginResquestDTO) {


        try {
            // Thread.sleep(Duration.ofSeconds(60));
            String[] datosAlumnos = autenticacionService.validarAlumno(loginResquestDTO);
            System.out.println("Resultado :");
            if (datosAlumnos == null) {
                return new LoginResponseDTO("01", "Error: Usuario no Encontrado", "", "");
            }

            return  new LoginResponseDTO("00","",datosAlumnos[0],datosAlumnos[1]);
        } catch (Exception e) {
            return new LoginResponseDTO("99", "Ocurrio un Problema", "", "");
        }

    }
}
