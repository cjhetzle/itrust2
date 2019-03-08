package edu.ncsu.csc.itrust2.controllers.api;

import static edu.ncsu.csc.itrust2.controllers.api.APIController.BASE_PATH;
import edu.ncsu.csc.itrust2.forms.admin.DrugForm;
import edu.ncsu.csc.itrust2.forms.hcp.PrescriptionForm;
import edu.ncsu.csc.itrust2.models.enums.TransactionType;
import edu.ncsu.csc.itrust2.models.persistent.Drug;
import edu.ncsu.csc.itrust2.models.persistent.Prescription;
import edu.ncsu.csc.itrust2.models.persistent.User;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Controller for handling prescription processes
 *
 * @author Nicholas Luther
 */
@RestController
@SuppressWarnings({"unchecked", "rawtypes"})
public class APIPrescriptionController {

    /**
     * Create a drug from a form and return the response
     *
     * @param drugF the drug form
     * @return the response entity
     */
    @PostMapping(BASE_PATH + "/drugs")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity createDrug(@RequestBody final DrugForm drugF) {
        try {
            final Drug drug = new Drug(drugF);
            drug.save();
            LoggerUtil.log(TransactionType.PRESCRIPTIONS_CREATE_DRUG,
                    SecurityContextHolder.getContext().getAuthentication().getName());
            return new ResponseEntity(drug, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity(
                    "Could not validate or save the Drug provided due to " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieve all drugs currently in the system
     *
     * @return the drugs
     */
    @GetMapping(BASE_PATH + "/drugs")
    public List<Drug> getDrugs() {
        LoggerUtil.log(TransactionType.PRESCRIPTIONS_RETRIEVE_DRUGS,
                    SecurityContextHolder.getContext().getAuthentication().getName());
        return Drug.getDrugs();
    }

    /**
     * Deletes a drug from the system
     *
     * @param id the id of the drug to delete
     * @return the response entity
     */
    @DeleteMapping(BASE_PATH + "/drugs/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteDrug(@PathVariable("id") final String id) {
        try {
            Drug.getById(id).delete();
            LoggerUtil.log(TransactionType.PRESCRIPTIONS_DELETE_DRUG,
                    SecurityContextHolder.getContext().getAuthentication().getName());
            return new ResponseEntity(HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity("Could not delete drug due to " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creates a prescription from a form
     *
     * @param pf the prescription form
     * @return the response entity
     */
    @PostMapping(BASE_PATH + "/prescriptions")
    @PreAuthorize("hasRole('ROLE_HCP')")
    public ResponseEntity createPrescription(@RequestBody final PrescriptionForm pf) {
        try {
            final Prescription p = new Prescription(pf);
            p.save();
            LoggerUtil.log(TransactionType.PRESCRIPTIONS_ASSIGN,
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    p.getPatient().getUsername());
            return new ResponseEntity(p, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity(
                    "Could not validate or save the Prescription provided due to " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all prescriptions currently in the system
     *
     * @return the prescriptions
     */
    @PreAuthorize("hasRole('ROLE_HCP')")
    @GetMapping(BASE_PATH + "/prescriptions")
    public List<Prescription> getPrescriptions() {
        final User self = User.getByName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        LoggerUtil.log(TransactionType.PRESCRIPTIONS_HCP_VIEW,
                    SecurityContextHolder.getContext().getAuthentication().getName());
        return Prescription.getForHCP(self.getId());
    }

    /**
     * Deletes a prescription
     *
     * @param id the id of the prescription to delete
     * @return the response entity
     */
    @PreAuthorize("hasRole('ROLE_HCP')")
    @DeleteMapping(BASE_PATH + "/prescriptions/{id}")
    public ResponseEntity deletePrescriptions(@PathVariable("id") final String id) {
        try {
            Prescription p = Prescription.getById(id);
            String username = p.getPatient().getUsername();
            p.delete();
            LoggerUtil.log(TransactionType.PRESCRIPTIONS_REVOKE,
                    SecurityContextHolder.getContext().getAuthentication().getName(), username);
            return new ResponseEntity(HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity("Could not delete prescription due to " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a list of all Prescriptions in the database
     *
     * @return list of prescriptions
     */
    @GetMapping(BASE_PATH + "/prescriptions/myprescriptions")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public List<Prescription> getMyPrescriptions() {
        final User self = User.getByName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        LoggerUtil.log(TransactionType.PRESCRIPTIONS_VIEW,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return Prescription.getForPatient(self.getId());
    }
}
