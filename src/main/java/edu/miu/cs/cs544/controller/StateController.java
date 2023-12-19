package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.dto.CountryDTO;
import edu.miu.cs.cs544.dto.StateDTO;
import edu.miu.cs.cs544.service.CountryService;
import edu.miu.cs.cs544.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<?> addState(@RequestBody State state){
        return new ResponseEntity<State>(stateService.addState(state), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllStates(){
        return new ResponseEntity<List<StateDTO>>( stateService.findAllStates(), HttpStatus.OK);
    }
    @GetMapping("/{state_id}")
    public ResponseEntity<?> getState(@PathVariable Integer state_id){
        return new ResponseEntity<StateDTO>(stateService.findById(state_id), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> updateState(@Valid @RequestBody StateDTO stateDTO){
        return new ResponseEntity<StateDTO>(stateService.updateState(stateDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{state_id}")
    public ResponseEntity<?> deleteState(@PathVariable Integer state_id){
        return new ResponseEntity<String>(stateService.deleteById(state_id), HttpStatus.OK);
    }

}
