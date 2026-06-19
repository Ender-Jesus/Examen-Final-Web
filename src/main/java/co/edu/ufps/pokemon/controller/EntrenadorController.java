package co.edu.ufps.pokemon.controller;

import co.edu.ufps.pokemon.dto.EntrenadorRequestDTO;
import co.edu.ufps.pokemon.dto.EntrenadorResponseDTO;
import co.edu.ufps.pokemon.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @PostMapping("/login")
    public ResponseEntity<EntrenadorResponseDTO> login(@RequestBody EntrenadorRequestDTO requestDTO) {
        String uuid = entrenadorService.registrar(requestDTO.getEmail());
        EntrenadorResponseDTO responseDTO = EntrenadorResponseDTO.builder()
                .uuid(uuid)
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
