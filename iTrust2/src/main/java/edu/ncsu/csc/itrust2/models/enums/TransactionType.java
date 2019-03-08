package edu.ncsu.csc.itrust2.models.enums;

/**
 * A TransactionType represents an event that took place in the system and that
 * is to be logged. This is used to provide a code that can easily be saved in
 * the database and a longer description of it that can be displayed to the
 * user. Also stores whether the event is patient-visible.
 *
 * As new functionality is added to iTrust2, add in new TransactionType codes
 * representing the event.
 *
 * @author Kai Presler-Marshall
 *
 */
public enum TransactionType {

    /**
     * Failed login
     */
    LOGIN_FAILURE ( 1, "Failed login", true ),
    /**
     * Successful login
     */
    LOGIN_SUCCESS ( 2, "Successful login", true ),
    /**
     * User logged out
     */
    LOGOUT ( 3, "Logged Out", true ),

    /**
     * New User created
     */
    CREATE_USER ( 100, "New user created", false ),
    /**
     * User was viewed
     */
    VIEW_USER ( 101, "Single user viewed", false ),
    /**
     * Multiple users viewed
     */
    VIEW_USERS ( 102, "List of users viewed", false ),
    /**
     * User deleted
     */
    DELETE_USER ( 103, "User deleted", false ),
    /**
     * User changed/updated
     */
    UPDATE_USER ( 104, "User updated", false ),

    /**
     * User viewed their demographics
     */
    VIEW_DEMOGRAPHICS ( 400, "Demographics viewed by user", true ),
    /**
     * User updated their demographics
     */
    ENTER_EDIT_DEMOGRAPHICS ( 410, "Demographics edited by user", true ),

    /**
     * Hospital created
     */
    CREATE_HOSPITAL ( 500, "New hospital created", false ),
    /**
     * Hospital viewed by user
     */
    VIEW_HOSPITAL ( 501, "Hospital viewed", false ),
    /**
     * Hospital modified by user
     */
    EDIT_HOSPITAL ( 502, "Hospital edited", false ),
    /**
     * Hospital deleted
     */
    DELETE_HOSPITAL ( 503, "Hospital deleted", false ),

    /**
     * Upcoming appointment viewed by Patient or HCP
     */
    VIEW_SCHEDULED_APPOINTMENT ( 611, "Upcoming appointment viewed", true ),
    /**
     * AppointmentRequest submitted by patient
     */
    APPOINTMENT_REQUEST_SUBMITTED ( 640, "Appointment requested by patient", true ),
    /**
     * AppointmentRequest viewed
     */
    APPOINTMENT_REQUEST_VIEWED ( 641, "Appointment request(s) viewed", true ),
    /**
     * AppointmentRequest canceled/deleted by patient
     */
    APPOINTMENT_REQUEST_DELETED ( 642, "Appointment request deleted by patient", true ),
    /**
     * AppointmentRequest approved by HCP
     */
    APPOINTMENT_REQUEST_APPROVED ( 650, "Appointment request approved by HCP", true ),
    /**
     * AppointmentRequest denied by HCP
     */
    APPOINTMENT_REQUEST_DENIED ( 651, "Appointment request denied by HCP", true ),
    /**
     * AppointmentRequest otherwise updated
     */
    APPOINTMENT_REQUEST_UPDATED ( 652, "Appointment request was updated", true ),

    /**
     * Create basic health metrics
     */
    OFFICE_VISIT_CREATE ( 800, "Create basic health metrics", true ),
    /**
     * HCP views basic health metrics
     */
    OFFICE_VISIT_HCP_VIEW ( 801, "HCP views basic health metrics", true ),
    /**
     * HCP edits basic health metrics
     */
    OFFICE_VISIT_EDIT ( 802, "HCP edits basic health metrics", true ),
    /**
     * Patient views basic health metrics for an office visit
     */
    OFFICE_VISIT_PATIENT_VIEW ( 810, "Patient views basic health metrics for an office visit", true ),

    /**
     * Admin creates a new drug
     */
    PRESCRIPTIONS_CREATE_DRUG ( 900, "Admin created drug", true ),
    /**
     * Admin deletes a drug
     */
    PRESCRIPTIONS_DELETE_DRUG ( 901, "Admin deleted drug", true ),
    /**
     * Someone retrieved the list of drugs stored in the system.
     */
    PRESCRIPTIONS_RETRIEVE_DRUGS ( 902, "List of drugs retrieved", true ),
    /**
     * HCP assigned a prescription
     */
    PRESCRIPTIONS_ASSIGN ( 910, "HCP prescribed a drug", true ),
    /**
     * HCP revokes a prescription
     */
    PRESCRIPTIONS_REVOKE ( 911, "HCP revoked a prescription", true ),
    /**
     * Patient viewed their prescriptions
     */
    PRESCRIPTIONS_VIEW ( 920, "Patient viewed prescriptions", true ),
    /**
     * HCP viewed their assigned prescriptions
     */
    PRESCRIPTIONS_HCP_VIEW ( 921, "HCP viewed prescriptions", true ),
    /** Admin created diagnosis */
    DIAGNOSIS_ADD ( 1000, "Admin created a diagnosis", true ),
    /** Admin deleted diagnosis */
    DIAGNOSIS_DELETE ( 1001, "Admin deleted a diagnosis", true ),
    /** Patient views their diagnoses */
    DIAGNOSIS_VIEW ( 1010, "Patient viewed their diagnoses", true ),
    /** HCP views list of diagnoses */
    DIAGNOSIS_LIST ( 1020, "HCP viewed diagnosis list", true ),
    /** User changed their password */
    PASSWORD_CHANGE ( 1100, "User changed password", true ),
    /** User reset their password */
    PASSWORD_RESET ( 1110, "User reset password", true ),
    /** User requested a reset token */
    PASSWORD_RESETTOKENSENT ( 1111, "Password reset token sent via email", true ),
    /** HCP edits patient's demographics */
    HCP_PATIENT_DEMOGRAPHICS ( 1200, "Patient's demographics edited by HCP", true );

    ;

    /**
     * Creates a TransactionType for logging events
     *
     * @param code
     *            Code of the event
     * @param description
     *            Description of the event that occurred
     * @param patientViewable
     *            Whether this logged event can be viewed by the patient
     *            involved
     */
    private TransactionType ( final int code, final String description, final boolean patientViewable ) {
        this.code = code;
        this.description = description;
        this.patientView = patientViewable;
    }

    /**
     * Code of the TransactionType, from the iTrust2 wiki.
     */
    private int     code;
    /**
     * Description of the event
     */
    private String  description;
    /**
     * Whether the patient can view the event
     */
    private boolean patientView;

    /**
     * Retrieves the code of this TransactionType
     *
     * @return Code of the event
     */
    public int getCode () {
        return code;
    }

    /**
     * Description of this TransactionType event
     *
     * @return Description of the event
     */
    public String getDescription () {
        return description;
    }

    /**
     * Retrieves if the Patient can view this event
     *
     * @return Patient viewable or not
     */
    public boolean isPatientViewable () {
        return patientView;
    }

}
