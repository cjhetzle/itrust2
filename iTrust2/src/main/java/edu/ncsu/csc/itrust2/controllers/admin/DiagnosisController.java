package edu.ncsu.csc.itrust2.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ncsu.csc.itrust2.forms.admin.DiagnosisForm;
import edu.ncsu.csc.itrust2.models.persistent.Diagnosis;

/**
 * Class that enables an Admin to add and delete diagnoses in the system.
 *
 * @author Kai Presler-Marshall modified by jmphipps
 *
 */
@Controller
public class DiagnosisController {

    /**
     * Creates the form page for the Add Diagnosis page
     *
     * @param model
     *            Data for the front end
     * @return Page to show to the user
     */
    @RequestMapping ( value = "/admin/addDiagnosis" )
    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    public String addDiagnosis ( final Model model ) {
        model.addAttribute( "DiagnosisForm", new DiagnosisForm() );
        return "/admin/addDiagnosis";
    }

    /**
     * Displays the form for an Admin to delete a Diagnosis from the system
     *
     * @param model
     *            Data for the front end
     * @return The page to display to the user
     */
    @RequestMapping ( value = "admin/deleteDiagnosis" )
    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    public String deleteDiagnosis ( final Model model ) {
        model.addAttribute( "diagnosis", Diagnosis.getDiagnosis() );
        return "admin/deleteDiagnosis";
    }
}
