package edu.ncsu.csc.itrust2.models.persistent;

import edu.ncsu.csc.itrust2.forms.hcp.PrescriptionForm;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.utils.DomainObjectCache;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 * Class to store patients prescription information
 *
 * @author Nicholas Luther
 */
@SuppressWarnings ( "serial" )
@Entity
@Table ( name = "Prescriptions" )
public class Prescription extends DomainObject<Prescription> implements Serializable {

    /**
     * In-memory cache that will store instances of the Hospital to avoid
     * retrieval trips to the database.
     */
    static private DomainObjectCache<String, Prescription> cache = new DomainObjectCache<String, Prescription>(
            Prescription.class );

    /**
     * Drug prescribed
     */
    @NotNull
    @ManyToOne
    private Drug                                           drug;

    /**
     * Patient the drug is prescribed to
     */
    @NotNull
    @ManyToOne
    @JoinColumn ( name = "patient_id" )
    private User                                           patient;

    /**
     * The hcp of this office visit
     */
    @NotNull
    @ManyToOne
    @JoinColumn ( name = "hcp_id" )
    private User                                           hcp;

    /**
     * Number of possible renewals
     */
    @NotNull
    private Integer                                        renewals;

    /**
     * Prescription start date
     */
    @NotNull
    @Temporal ( javax.persistence.TemporalType.DATE )
    private Calendar                                       startDate;

    /**
     * Prescription end date
     */
    @NotNull
    @Temporal ( javax.persistence.TemporalType.DATE )
    private Calendar                                       endDate;

    /**
     * Dosage in milligrams
     */
    @NotNull
    @Range ( min = 1 )
    private Integer                                        dosage;

    /**
     * HCP's notes to patient
     */
    @NotNull
    private String                                         notes;

    /**
     * The id of this prescription
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long                                           id;

    /**
     * Blank constructor for Hibernate
     */
    public Prescription () {
    }

    /**
     * Construct from PrescriptionForm
     *
     * @param pf
     *            the prescription form
     * @throws Exception
     *             exception to pass messages to the browser console
     */
    public Prescription ( PrescriptionForm pf ) throws Exception {
        if ( pf.getNotes() == null ) {
            notes = "";
        }
        else {
            notes = pf.getNotes();
        }

        drug = pf.getDrug();
        patient = User.getByName( pf.getPatient().getSelf().getId() );
        hcp = User.getByNameAndRole( pf.getHcp(), Role.ROLE_HCP );
        renewals = pf.getRenewals();
        dosage = pf.getDosage();

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy", Locale.ENGLISH );
            final Date parsedDate = sdf.parse( pf.getStartDate() );
            final Calendar c = Calendar.getInstance();
            c.setTime( parsedDate );
            startDate = c;
        }
        catch ( ParseException | NullPointerException e ) {
            throw new Exception( "Start date format is invalid." );
        }
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy", Locale.ENGLISH );
            final Date parsedDate = sdf.parse( pf.getEndDate() );
            final Calendar c = Calendar.getInstance();
            c.setTime( parsedDate );
            endDate = c;
        }
        catch ( ParseException | NullPointerException e ) {
            throw new Exception( "End date format is invalid." );
        }
    }

    /**
     * Retrieve all Prescriptions from the database
     *
     * @return Prescriptions found
     */
    @SuppressWarnings ( "unchecked" )
    public static List<Prescription> getPrescriptions () {
        return (List<Prescription>) getAll( Prescription.class );
    }

    /**
     * Retrieve all matching Prescriptions from the database that match a where
     * clause provided.
     *
     * @param where
     *            Clause to match by
     * @return The matching Prescriptions
     */
    @SuppressWarnings ( "unchecked" )
    private static List<Prescription> getWhere ( final String where ) {
        return (List<Prescription>) getWhere( Prescription.class, where );
    }

    /**
     * Retrieve a Prescription by its NDC.
     *
     * @param id
     *            The ID of the Prescription
     * @return The Prescription, if found, or null if not found.
     */
    public static Prescription getById ( final String id ) {
        Prescription request = cache.get( id );
        if ( null == request ) {
            try {
                request = getWhere( "id = '" + id + "'" ).get( 0 );
                cache.put( id, request );
            }
            catch ( final Exception e ) {
                // Exception ignored
            }
        }
        return request;
    }

    /**
     * Get all prescriptions for a specific patient
     *
     * @param patientName
     *            the name of the patient
     * @return the prescriptions of the queried patient
     */
    public static List<Prescription> getForPatient ( final String patientName ) {
        return getWhere( " patient_id = '" + patientName + "'" );
    }

    /**
     * Get all prescriptions for a specific HCP
     *
     * @param hcpName
     *            the name of the HCP
     * @return the prescriptions of the queried HCP
     */
    public static List<Prescription> getForHCP ( final String hcpName ) {
        return getWhere( " hcp_id = '" + hcpName + "'" );
    }

    /**
     * Retrieves the drug
     *
     * @return the drug
     */
    public Drug getDrug () {
        return drug;
    }

    // /**
    // * Set the drug
    // *
    // * @param drug the drug to set
    // */
    // public void setDrug(Drug drug) {
    // this.drug = drug;
    // }

    /**
     * Retrieves the number of renewals
     *
     * @return the number of renewals
     */
    public int getRenewals () {
        return renewals;
    }
    //
    // /**
    // * Set the number of renewals
    // *
    // * @param renewals the number of renewals
    // */
    // public void setRenewals(int renewals) {
    // this.renewals = renewals;
    // }

    /**
     * Retrieve the start date
     *
     * @return the start date
     */
    public Calendar getStartDate () {
        return startDate;
    }

    // /**
    // * Set the start date
    // *
    // * @param startDate the start date of the prescription
    // */
    // public void setStartDate(Calendar startDate) {
    // this.startDate = startDate;
    // }

    /**
     * Retrieve the end date
     *
     * @return the end date of the prescription
     */
    public Calendar getEndDate () {
        return endDate;
    }

    // /**
    // * Set the end date
    // *
    // * @param endDate the end date
    // */
    // public void setEndDate(Calendar endDate) {
    // this.endDate = endDate;
    // }

    /**
     * Retrieve the doctors notes
     *
     * @return the doctors notes
     */
    public String getNotes () {
        return notes;
    }
    //
    // /**
    // * Sets the doctors notes
    // *
    // * @param notes the doctors notes
    // */
    // public void setNotes(String notes) {
    // this.notes = notes;
    // }

    /**
     * Retrieve the dosage
     *
     * @return the dosage
     */
    public int getDosage () {
        return dosage;
    }

    // /**
    // * Set the dosage
    // *
    // * @param dosage the dosage
    // */
    // public void setDosage(int dosage) {
    // this.dosage = dosage;
    // }

    /**
     * Retrieve the patient
     *
     * @return the patient
     */
    public User getPatient () {
        return patient;
    }

    /**
     * Get the id of the prescription
     *
     * @return the auto-generated id
     */
    @Override
    public Serializable getId () {
        return id;
    }
}
