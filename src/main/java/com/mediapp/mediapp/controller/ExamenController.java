package com.mediapp.mediapp.controller;

import com.mediapp.mediapp.dto.ExamenDTO;
import com.mediapp.mediapp.exception.ModeloNotFoundException;
import com.mediapp.mediapp.model.Examen;
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
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExamenDTO>> listar() throws Exception {

        List<ExamenDTO> lista = examenService.listar().stream()
                .map(p -> mapper.map(p,ExamenDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
     public ResponseEntity<ExamenDTO> getExamenById(@PathVariable Integer id) throws Exception {
        ExamenDTO dtoResponse;
        Examen obj = examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }else{
            dtoResponse = mapper.map(obj,ExamenDTO.class);
        }
        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ExamenDTO> registrar(@RequestBody  @Valid ExamenDTO ExamenDTO) throws Exception{
//        Examen Examen = mapper.map(ExamenDTO,Examen.class);
//        Examen obj= examenService.registrar(Examen);
//        ExamenDTO dtoResponse= mapper.map(obj,ExamenDTO.class);
//        return new ResponseEntity<>(dtoResponse,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody  @Valid ExamenDTO ExamenDTO) throws Exception{
        Examen Examen = mapper.map(ExamenDTO,Examen.class);
        Examen obj= examenService.registrar(Examen);
        ExamenDTO dtoResponse= mapper.map(obj,ExamenDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getIdExamen()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ExamenDTO> update(@RequestBody @Valid  ExamenDTO dtoRequest) throws Exception{
        Examen Examen = mapper.map(dtoRequest,Examen.class);
        Examen obj= examenService.modificar(Examen);
        ExamenDTO dtoResponse= mapper.map(obj,ExamenDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK); // esto se guarda en el header y te ahorras el body
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void registrar(Integer id) throws Exception{

        Examen obj= examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        examenService.eliminar(id);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ExamenDTO> listaerHateoas(@PathVariable("id") Integer id) throws Exception{
        Examen obj= examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        ExamenDTO dto = mapper.map(obj,ExamenDTO.class);

        EntityModel<ExamenDTO> recurso =  EntityModel.of(dto);
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getExamenById(id));
        recurso.add(link1.withRel("Examen-link"));
        return recurso;
    }

    //http://localhost:8080/v3/api-docs
    //http://localhost:8080/swagger-ui.hmtl
}
