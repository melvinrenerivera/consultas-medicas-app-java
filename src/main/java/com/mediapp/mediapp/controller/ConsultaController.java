package com.mediapp.mediapp.controller;

import com.mediapp.mediapp.dto.ConsultaDTO;
import com.mediapp.mediapp.dto.ConsultaListaExamenDTO;
import com.mediapp.mediapp.dto.EspecialidadDTO;
import com.mediapp.mediapp.exception.ModeloNotFoundException;
import com.mediapp.mediapp.model.Consulta;
import com.mediapp.mediapp.model.Especialidad;
import com.mediapp.mediapp.model.Examen;
import com.mediapp.mediapp.service.ConsultaService;
import com.mediapp.mediapp.service.EspecialidadService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
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
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService examenService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listar() throws Exception {

        List<ConsultaDTO> lista = examenService.listar().stream()
                .map(p -> mapper.map(p,ConsultaDTO.class)).collect(Collectors.toList());

        lista.add(new ConsultaDTO());
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
     public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable Integer id) throws Exception {
        ConsultaDTO dtoResponse;
        Consulta obj = examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }else{
            dtoResponse = mapper.map(obj,ConsultaDTO.class);
        }
        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ConsultaDTO> registrar(@RequestBody  @Valid ConsultaDTO ConsultaDTO) throws Exception{
//        Consulta Consulta = mapper.map(ConsultaDTO,Consulta.class);
//        Consulta obj= examenService.registrar(Consulta);
//        ConsultaDTO dtoResponse= mapper.map(obj,ConsultaDTO.class);
//        return new ResponseEntity<>(dtoResponse,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody  @Valid ConsultaListaExamenDTO dtoRequest) throws Exception{

      //  mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
      //  Consulta c = mapper.map(dtoRequest,Consulta.class);

        Consulta consulta = mapper.map(dtoRequest.getConsulta(),Consulta.class);
        List<Examen> examenes = mapper.map(dtoRequest.getLstExamen(),new TypeToken<List<Examen>>() {}.getType());

        Consulta obj= examenService.registrarConsulta(consulta,examenes);
        ConsultaDTO dtoResponse= mapper.map(obj,ConsultaDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getIdConsulta()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ConsultaDTO> update(@RequestBody @Valid  ConsultaDTO dtoRequest) throws Exception{
        Consulta Consulta = mapper.map(dtoRequest,Consulta.class);
        Consulta obj= examenService.modificar(Consulta);
        ConsultaDTO dtoResponse= mapper.map(obj,ConsultaDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK); // esto se guarda en el header y te ahorras el body
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void registrar(Integer id) throws Exception{

        Consulta obj= examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        examenService.eliminar(id);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ConsultaDTO> listaerHateoas(@PathVariable("id") Integer id) throws Exception{
        Consulta obj= examenService.listarPorId(id);
        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
        }
        ConsultaDTO dto = mapper.map(obj,ConsultaDTO.class);

        EntityModel<ConsultaDTO> recurso =  EntityModel.of(dto);
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getConsultaById(id));
        recurso.add(link1.withRel("Consulta-link"));
        return recurso;
    }

    //http://localhost:8080/v3/api-docs
    //http://localhost:8080/swagger-ui.hmtl
}
