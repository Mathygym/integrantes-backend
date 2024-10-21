package pe.edu.cibertec.integrantes_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.integrantes_backend.dto.LoginResponseDTO;
import pe.edu.cibertec.integrantes_backend.dto.LoginResquestDTO;
import pe.edu.cibertec.integrantes_backend.service.AutenticacionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/get-integrantes")
    public List<Map<String, String>> getIntegrantes() throws IOException {
        List<String[]> integrantes = autenticacionService.listarIntegrantes();
        List<Map<String, String>> response = new ArrayList<>();

        for (String[] integrante : integrantes) {
            Map<String, String> studentData = new HashMap<>();
            studentData.put("nombres", integrante[0]);  // Names
            studentData.put("apellidos", integrante[1]);  // Surnames
            response.add(studentData);
        }

        return response;
    }

}
