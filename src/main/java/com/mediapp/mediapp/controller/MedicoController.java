package com.mediapp.mediapp.controller;

import com.mediapp.mediapp.dto.MedicoDTO;
import com.mediapp.mediapp.dto.PacienteDTO;
import com.mediapp.mediapp.exception.ModeloNotFoundException;
import com.mediapp.mediapp.model.Medico;
import com.mediapp.mediapp.model.Paciente;
import com.mediapp.mediapp.service.MedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar() throws Exception {

        List<MedicoDTO> lista = medicoService.listar().stream()
                .map(p -> mapper.map(p,MedicoDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
     public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable Integer id) throws Exception {
        MedicoDTO dtoResponse;
        Medico obj = medicoService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }else{
            dtoResponse = mapper.map(obj,MedicoDTO.class);
        }
        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<MedicoDTO> registrar(@RequestBody  @Valid MedicoDTO MedicoDTO) throws Exception{
//        Medico Medico = mapper.map(MedicoDTO,Medico.class);
//        Medico obj= MedicoService.registrar(Medico);
//        MedicoDTO dtoResponse= mapper.map(obj,MedicoDTO.class);
//        return new ResponseEntity<>(dtoResponse,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody  @Valid MedicoDTO MedicoDTO) throws Exception{
        Medico Medico = mapper.map(MedicoDTO,Medico.class);
        Medico obj= medicoService.registrar(Medico);
        MedicoDTO dtoResponse= mapper.map(obj,MedicoDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getIdMedico()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<MedicoDTO> update(@RequestBody @Valid  MedicoDTO dtoRequest) throws Exception{
        Medico Medico = mapper.map(dtoRequest,Medico.class);
        Medico obj= medicoService.modificar(Medico);
        MedicoDTO dtoResponse= mapper.map(obj,MedicoDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK); // esto se guarda en el header y te ahorras el body
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void registrar(Integer id) throws Exception{

        Medico obj= medicoService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        medicoService.eliminar(id);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<MedicoDTO> listaerHateoas(@PathVariable("id") Integer id) throws Exception{
        Medico obj= medicoService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        MedicoDTO dto = mapper.map(obj,MedicoDTO.class);

        EntityModel<MedicoDTO> recurso =  EntityModel.of(dto);
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(id));
        recurso.add(link1.withRel("Medico-link"));
        return recurso;
    }

    //http://localhost:8080/v3/api-docs
    //http://localhost:8080/swagger-ui.hmtl
}
