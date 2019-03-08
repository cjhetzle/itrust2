package edu.ncsu.csc.itrust2.controllers.hcp;

import edu.ncsu.csc.itrust2.forms.hcp.PrescriptionForm;
import edu.ncsu.csc.itrust2.models.persistent.Prescription;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle prescriptions for HCPs
 *
 * @author Nicholas Luther
 */
@Controller
public class PrescriptionController {

    /**
     * Redirects an HCP to assignPrescription.html and gives the model the relevant form.
     *
     * @param model the webpage model
     * @return the path to assignPrescription
     */
    @RequestMapping(value = "/hcp/assignPrescription")
    @PreAuthorize("hasRole('ROLE_HCP')")
    public String assignPrescription(final Model model) {
        model.addAttribute("PrescriptionForm", new PrescriptionForm());
        return "/hcp/assignPrescription";
    }

    /**
     * Redirects an HCP to deletePrescription.html and gives the model the prescriptions
     * the HCP prescribed.
     *
     * @param model the webpage model
     * @return the path to deletePrescription
     */
    @RequestMapping(value = "/hcp/deletePrescription")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_HCP')")
    public String deletePrescriptions(final Model model) {
        model.addAttribute("prescriptions", Prescription.getPrescriptions());
        return "/hcp/deletePrescription";
    }
}
