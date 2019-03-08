package edu.ncsu.csc.itrust2.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage basic abilities for Admin roles
 *
 * @author Kai Presler-Marshall
 *
 */

@Controller
public class AdminController {

    /**
     * Returns the admin for the given model
     *
     * @param model
     *            model to check
     * @return role
     */
    @RequestMapping ( value = "admin/index" )
    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    public String index ( final Model model ) {
        return edu.ncsu.csc.itrust2.models.enums.Role.ROLE_ADMIN.getLanding();
    }

    /**
     * Creates the form page for the change password page
     *
     * @param model
     *            Data for the front end
     * @return Page to show to the user
     */
    @RequestMapping ( value = "/admin/changePassword" )
    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    public String changePassword ( final Model model ) {
        return "/admin/changePassword";
    }
}
