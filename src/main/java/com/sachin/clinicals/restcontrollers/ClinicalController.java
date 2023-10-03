package com.sachin.clinicals.restcontrollers;

import com.sachin.clinicals.model.ClinicalData;
import com.sachin.clinicals.model.Patient;
import com.sachin.clinicals.repository.ClinicalDataRepository;
import com.sachin.clinicals.repository.PatientRepository;
import com.sachin.clinicals.restcontrollers.dto.ClinicalDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalController {

    private ClinicalDataRepository repository;
    private PatientRepository patientRepository;

    @Autowired
    ClinicalController(ClinicalDataRepository repository, PatientRepository patientRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
    }

    @RequestMapping(value = "/clinicals", method = RequestMethod.POST)
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest clinicalDataRequest) {
        Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
        ClinicalData data = new ClinicalData();
        data.setComponentName(clinicalDataRequest.getComponentName());
        data.setComponentValue(clinicalDataRequest.getComponentValue());
        data.setPatient(patient);
        return repository.save(data);
    }

    @RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
    public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,
                                              @PathVariable("componentName") String componentName) {
        return repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
    }

}
