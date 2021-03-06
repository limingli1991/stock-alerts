package ar.com.sac.controllers;

import ar.com.sac.model.geneticAlgorithm.GeneticAlgorithmParameters;
import ar.com.sac.model.geneticAlgorithm.GeneticAlgorithmResults;
import ar.com.sac.services.GeneticAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
@RequestMapping("/stock-alerts/genetic-algorithm")
public class GeneticAlgorithmController {
      
      @Autowired
      private GeneticAlgorithmService geneticAlgorithmService;
      
      @RequestMapping(method = RequestMethod.POST)
      public ResponseEntity<GeneticAlgorithmResults> runGeneticAlgorithm( @RequestBody GeneticAlgorithmParameters parameters) throws Exception {
         return new ResponseEntity<GeneticAlgorithmResults>( geneticAlgorithmService.runGeneticAlgorithm( parameters ), HttpStatus.OK );
      }
}
