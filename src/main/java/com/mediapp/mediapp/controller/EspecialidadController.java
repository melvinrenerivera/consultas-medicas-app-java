package com.mediapp.mediapp.controller;

import com.mediapp.mediapp.dto.EspecialidadDTO;
import com.mediapp.mediapp.dto.ExamenDTO;
import com.mediapp.mediapp.exception.ModeloNotFoundException;
import com.mediapp.mediapp.model.Especialidad;
import com.mediapp.mediapp.model.Examen;
import com.mediapp.mediapp.service.EspecialidadService;
import com.mediapp.mediapp.service.ExamenService;
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
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService examenService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> listar() throws Exception {

        List<EspecialidadDTO> lista = examenService.listar().stream()
                .map(p -> mapper.map(p,EspecialidadDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
     public ResponseEntity<EspecialidadDTO> getEspecialidadById(@PathVariable Integer id) throws Exception {
        EspecialidadDTO dtoResponse;
        Especialidad obj = examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }else{
            dtoResponse = mapper.map(obj,EspecialidadDTO.class);
        }
        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<EspecialidadDTO> registrar(@RequestBody  @Valid EspecialidadDTO EspecialidadDTO) throws Exception{
//        Especialidad Especialidad = mapper.map(EspecialidadDTO,Especialidad.class);
//        Especialidad obj= examenService.registrar(Especialidad);
//        EspecialidadDTO dtoResponse= mapper.map(obj,EspecialidadDTO.class);
//        return new ResponseEntity<>(dtoResponse,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody  @Valid EspecialidadDTO EspecialidadDTO) throws Exception{
        Especialidad Especialidad = mapper.map(EspecialidadDTO,Especialidad.class);
        Especialidad obj= examenService.registrar(Especialidad);
        EspecialidadDTO dtoResponse= mapper.map(obj,EspecialidadDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getIdEspecialidad()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<EspecialidadDTO> update(@RequestBody @Valid  EspecialidadDTO dtoRequest) throws Exception{
        Especialidad Especialidad = mapper.map(dtoRequest,Especialidad.class);
        Especialidad obj= examenService.modificar(Especialidad);
        EspecialidadDTO dtoResponse= mapper.map(obj,EspecialidadDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK); // esto se guarda en el header y te ahorras el body
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void registrar(Integer id) throws Exception{

        Especialidad obj= examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        examenService.eliminar(id);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<EspecialidadDTO> listaerHateoas(@PathVariable("id") Integer id) throws Exception{
        Especialidad obj= examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        EspecialidadDTO dto = mapper.map(obj,EspecialidadDTO.class);

        EntityModel<EspecialidadDTO> recurso =  EntityModel.of(dto);
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEspecialidadById(id));
        recurso.add(link1.withRel("Especialidad-link"));
        return recurso;
    }

    //http://localhost:8080/v3/api-docs
    //http://localhost:8080/swagger-ui.hmtl
}
