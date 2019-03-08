package edu.ncsu.csc.itrust2.controllers.admin;

import edu.ncsu.csc.itrust2.forms.admin.DrugForm;
import edu.ncsu.csc.itrust2.models.persistent.Drug;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controls the redirection to and from drugs for an admin
 *
 * @author Nicholas Luther
 */
@Controller
public class AdminDrugController {

    /**
     * Redirects an admin so they can add a drug to the system
     *
     * @param model the system model
     * @return the correct path
     */
    @RequestMapping(value = "/admin/addDrug")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDrug(final Model model) {
        model.addAttribute("DrugForm", new DrugForm());
        return "/admin/addDrug";
    }

    /**
     * Redirects a user so they can delete a drug from the system
     *
     * @param model the system model
     * @return the correct path to deleteDrug.html
     */
    @RequestMapping(value = "/admin/deleteDrug")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteDrug(final Model model) {
        model.addAttribute("drugs", Drug.getDrugs());
        return "/admin/deleteDrug";
    }

}
