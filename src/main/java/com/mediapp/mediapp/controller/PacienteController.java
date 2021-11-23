package com.mediapp.mediapp.controller;

import com.mediapp.mediapp.dto.PacienteDTO;
import com.mediapp.mediapp.exception.ModeloNotFoundException;
import com.mediapp.mediapp.model.Paciente;
import com.mediapp.mediapp.service.PacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() throws Exception {

        List<PacienteDTO> lista = pacienteService.listar().stream()
                .map(p -> mapper.map(p,PacienteDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
     public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Integer id) throws Exception {
        PacienteDTO dtoResponse;
        Paciente obj = pacienteService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }else{
            dtoResponse = mapper.map(obj,PacienteDTO.class);
        }
        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<PacienteDTO> registrar(@RequestBody  @Valid PacienteDTO pacienteDTO) throws Exception{
//        Paciente paciente = mapper.map(pacienteDTO,Paciente.class);
//        Paciente obj= pacienteService.registrar(paciente);
//        PacienteDTO dtoResponse= mapper.map(obj,PacienteDTO.class);
//        return new ResponseEntity<>(dtoResponse,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody  @Valid PacienteDTO pacienteDTO) throws Exception{
        Paciente paciente = mapper.map(pacienteDTO,Paciente.class);
        Paciente obj= pacienteService.registrar(paciente);
        PacienteDTO dtoResponse= mapper.map(obj,PacienteDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getIdPaciente()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<PacienteDTO> update(@RequestBody @Valid  PacienteDTO dtoRequest) throws Exception{
        Paciente paciente = mapper.map(dtoRequest,Paciente.class);
        Paciente obj= pacienteService.modificar(paciente);
        PacienteDTO dtoResponse= mapper.map(obj,PacienteDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK); // esto se guarda en el header y te ahorras el body
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void registrar(Integer id) throws Exception{

        Paciente obj= pacienteService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        pacienteService.eliminar(id);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<PacienteDTO> listaerHateoas(@PathVariable("id") Integer id) throws Exception{
        Paciente obj= pacienteService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        PacienteDTO dto = mapper.map(obj,PacienteDTO.class);

        EntityModel<PacienteDTO> recurso =  EntityModel.of(dto);
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(id));
        recurso.add(link1.withRel("paciente-link"));
        return recurso;
    }

    //http://localhost:8080/v3/api-docs
    //http://localhost:8080/swagger-ui.hmtl
}
