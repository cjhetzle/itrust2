package edu.ncsu.csc.itrust2.models.persistent;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.ncsu.csc.itrust2.forms.hcp.OfficeVisitForm;
import edu.ncsu.csc.itrust2.models.enums.HouseholdSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.PatientSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.utils.DomainObjectCache;

/**
 * Object persisted in the database that represents the BasicHealthMetrics of a
 * patient's office visit.
 *
 * @author Matthew Gray
 */

@Entity
@Table ( name = "BasicHealthMetrics" )
public class BasicHealthMetrics extends DomainObject<BasicHealthMetrics> {
    /**
     * In-memory cache that will store instances of the BasicHealthMetrics to
     * avoid retrieval trips to the database.
     */
    static private DomainObjectCache<Long, BasicHealthMetrics> cache = new DomainObjectCache<Long, BasicHealthMetrics>(
            BasicHealthMetrics.class );

    /**
     * Retrieve an BasicHealthMetrics by its numerical ID.
     *
     * @param id
     *            The ID (as assigned by the DB) of the BasicHealthMetrics
     * @return The BasicHealthMetrics, if found, or null if not found.
     */
    public static BasicHealthMetrics getById ( final Long id ) {
        BasicHealthMetrics request = cache.get( id );
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
     * Retrieve a List of all BasicHealthMetrics from the database. Can be
     * filtered further once retrieved. Will return the BasicHealthMetrics
     * sorted by date.
     *
     * @return A List of all BasicHealthMetrics saved in the database
     */
    @SuppressWarnings ( "unchecked" )
    public static List<BasicHealthMetrics> getBasicHealthMetrics () {
        final List<BasicHealthMetrics> requests = (List<BasicHealthMetrics>) getAll( BasicHealthMetrics.class );
        requests.sort( new Comparator<BasicHealthMetrics>() {
            @Override
            public int compare ( final BasicHealthMetrics o1, final BasicHealthMetrics o2 ) {
                return o1.getId().compareTo( o2.getId() );
            }
        } );
        return requests;
    }

    /**
     * Used so that Hibernate can construct and load objects
     */
    public BasicHealthMetrics () {
    }

    /**
     * Retrieve a List of BasicHealthMetrics that meets the given where clause.
     * Clause is expected to be valid SQL.
     *
     * @param where
     *            The WhereClause to find BasicHealthMetrics by
     * @return The matching list
     */
    @SuppressWarnings ( "unchecked" )
    private static List<BasicHealthMetrics> getWhere ( final String where ) {
        return (List<BasicHealthMetrics>) getWhere( BasicHealthMetrics.class, where );
    }

    /**
     * Retrieves all BasicHealthMetrics for the Patient provided.
     *
     * @param patientName
     *            Name of the patient
     * @return All of their BasicHealthMetrics
     */
    public static List<BasicHealthMetrics> getBasicHealthMetricsForPatient ( final String patientName ) {
        return getWhere( " patient_id = '" + patientName + "'" );
    }

    /**
     * Retrieves all BasicHealthMetrics for the HCP provided
     *
     * @param hcpName
     *            Name of the HCP
     * @return All BasicHealthMetrics involving this HCP
     */
    public static List<BasicHealthMetrics> getBasicHealthMetricsForHCP ( final String hcpName ) {
        return getWhere( " hcp_id = '" + hcpName + "' " );
    }

    /**
     * Retrieves all BasicHealthMetrics for the HCP _and_ Patient provided. This
     * is the intersection of the requests -- namely, only the ones where both
     * the HCP _and_ Patient are on the request.
     *
     * @param hcpName
     *            Name of the HCP
     * @param patientName
     *            Name of the Patient
     * @return The list of matching BasicHealthMetrics
     */
    public static List<BasicHealthMetrics> getBasicHealthMetricsForHCPAndPatient ( final String hcpName,
            final String patientName ) {
        return getWhere( " hcp_id = '" + hcpName + "'" + " AND patient_id = '" + patientName + "' " );
    }

    /**
     * Handles conversion between an OfficeVisitForm (the form with
     * user-provided data) and a BasicHealthMetrics object that contains
     * validated information These two classes are closely intertwined to handle
     * validated persistent information and text-based information that is then
     * displayed back to the user.
     *
     * @param ovf
     *            OfficeVisitForm to convert from
     * @throws ParseException
     *             Error in parsing form.
     */
    public BasicHealthMetrics ( final OfficeVisitForm ovf ) throws ParseException {
        setPatient( User.getByNameAndRole( ovf.getPatient(), Role.ROLE_PATIENT ) );
        setHcp( User.getByNameAndRole( ovf.getHcp(), Role.ROLE_HCP ) );

        setDiastolic( ovf.getDiastolic() );
        setHdl( ovf.getHdl() );
        setHeight( ovf.getHeight() );
        setHouseSmokingStatus( ovf.getHouseSmokingStatus() );
        setHeadCircumference( ovf.getHeadCircumference() );
        setLdl( ovf.getLdl() );
        setPatientSmokingStatus( ovf.getPatientSmokingStatus() );
        setSystolic( ovf.getSystolic() );
        setTri( ovf.getTri() );
        setWeight( ovf.getWeight() );
    }

    /**
     * ID of the AppointmentRequest
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id;

    /**
     * Retrieves the ID of the AppointmentRequest
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * Sets the ID of the AppointmentRequest
     *
     * @param id
     *            The new ID of the AppointmentRequest. For Hibernate.
     */
    @SuppressWarnings ( "unused" )
    private void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Height or length of the person. Up to a 3-digit number and potentially
     * one digit of decimal precision. > 0
     */
    private Float                  height;

    /**
     * Weight of the person. Up to a 3-digit number and potentially one digit of
     * decimal precision. > 0
     */
    private Float                  weight;

    /**
     * Head circumference of the person. Up to a 3-digit number and potentially
     * one digit of decimal precision. > 0
     */
    private Float                  headCircumference;

    /**
     * Systolic blood pressure. 3-digit positive number.
     */
    private Integer                systolic;

    /**
     * Diastolic blood pressure. 3-digit positive number.
     */
    private Integer                diastolic;

    /**
     * HDL cholesterol. Between 0 and 90 inclusive.
     */
    private Integer                hdl;

    /**
     * LDL cholesterol. Between 0 and 600 inclusive.
     */
    private Integer                ldl;

    /**
     * Triglycerides cholesterol. Between 100 and 600 inclusive.
     */
    private Integer                tri;

    /**
     * Smoking status of the patient's household.
     */
    private HouseholdSmokingStatus houseSmokingStatus;

    /**
     * Smoking status of the patient.
     */
    private PatientSmokingStatus   patientSmokingStatus;

    /**
     * The Patient who is associated with this AppointmentRequest
     */
    @NotNull
    @ManyToOne
    @JoinColumn ( name = "patient_id" )
    private User                   patient;

    /**
     * The HCP who is associated with this AppointmentRequest
     */
    @NotNull
    @ManyToOne
    @JoinColumn ( name = "hcp_id" )
    private User                   hcp;

    /**
     * Retrieves the User object for the Patient for the AppointmentRequest
     *
     * @return The associated Patient
     */
    public User getPatient () {
        return patient;
    }

    /**
     * Sets the Patient for the AppointmentRequest
     *
     * @param patient
     *            The User object for the Patient on the Request
     */
    public void setPatient ( final User patient ) {
        this.patient = patient;
    }

    /**
     * Gets the User object for the HCP on the request
     *
     * @return The User object for the HCP on the request
     */
    public User getHcp () {
        return hcp;
    }

    /**
     * Sets the User object for the HCP on the AppointmentRequest
     *
     * @param hcp
     *            User object for the HCP on the Request
     */
    public void setHcp ( final User hcp ) {
        this.hcp = hcp;
    }

    /**
     * Gets the height
     *
     * @return the height
     */
    public Float getHeight () {
        return height;
    }

    /**
     * Sets the height
     *
     * @param height
     *            the height to set
     */
    public void setHeight ( final Float height ) {
        if ( height == null ) {
            return;
        }

        if ( !Pattern.matches( "^[0-9]{1,3}(\\.[0-9]?)?$", String.valueOf( height ) ) ) {
            throw new IllegalArgumentException( "Height cannot be less than .1 or greater than 999.9" );
        }
        this.height = height;
    }

    /**
     * Gets the weight
     *
     * @return the weight
     */
    public Float getWeight () {
        return weight;
    }

    /**
     * Sets the weight
     *
     * @param weight
     *            the weight to set, min .1, max 999.9
     */
    public void setWeight ( final Float weight ) {
        if ( weight == null ) {
            return;
        }
        if ( !Pattern.matches( "^[0-9]{1,3}(\\.[0-9]?)?$", String.valueOf( weight ) ) ) {
            throw new IllegalArgumentException( "Weight cannot be less than .1 or greater than 999.9" );
        }
        this.weight = weight;
    }

    /**
     * Gets the head circumference
     *
     * @return the head circumference
     */
    public Float getHeadCircumference () {
        return headCircumference;
    }

    /**
     * Sets the headCircumference
     *
     * @param headCircumference
     *            the headCircumference to set
     */
    public void setHeadCircumference ( final Float headCircumference ) {
        if ( headCircumference == null ) {
            return;
        }
        if ( !Pattern.matches( "^[0-9]{1,3}(\\.[0-9]?)?$", String.valueOf( headCircumference ) ) ) {
            throw new IllegalArgumentException( "Head circumference cannot be less than .1 or greater than 999.9" );
        }
        this.headCircumference = headCircumference;
    }

    /**
     * Gets the diastolic blood pressure
     *
     * @return the diastolic
     */
    public Integer getDiastolic () {
        return diastolic;
    }

    /**
     * Sets the diastolic blood pressure
     *
     * @param diastolic
     *            the diastolic to set
     */
    public void setDiastolic ( final Integer diastolic ) {
        if ( diastolic == null ) {
            return;
        }
        if ( diastolic < 0 || diastolic > 999 ) {
            throw new IllegalArgumentException( "Diastolic must be a 3 digit positive number." );
        }
        this.diastolic = diastolic;
    }

    /**
     * Gets the systolic blood pressure
     *
     * @return the systolic
     */
    public Integer getSystolic () {
        return systolic;
    }

    /**
     * Sets the systolic blood pressure
     *
     * @param systolic
     *            the systolic to set
     */
    public void setSystolic ( final Integer systolic ) {
        if ( systolic == null ) {
            return;
        }
        if ( systolic < 0 || systolic > 999 ) {
            throw new IllegalArgumentException( "Systolic must be a 3 digit positive number." );
        }
        this.systolic = systolic;
    }

    /**
     * Gets HDL cholesterol.
     *
     * @return the hdl
     */
    public Integer getHdl () {
        return hdl;
    }

    /**
     * Sets HDL cholesterol. Between 0 and 90 inclusive.
     *
     * @param hdl
     *            the hdl to set
     */
    public void setHdl ( final Integer hdl ) {
        if ( hdl == null ) {
            return;
        }
        if ( hdl < 0 || hdl > 90 ) {
            throw new IllegalArgumentException( "HDL must be between 0 and 90 inclusive." );
        }
        this.hdl = hdl;
    }

    /**
     * Gets the LDL cholesterol.
     *
     * @return the ldl
     */
    public Integer getLdl () {
        return ldl;
    }

    /**
     * Sets LDL cholesterol. Between 0 and 600 inclusive.
     *
     * @param ldl
     *            the ldl to set
     */
    public void setLdl ( final Integer ldl ) {
        if ( ldl == null ) {
            return;
        }
        if ( ldl < 0 || ldl > 600 ) {
            throw new IllegalArgumentException( "LDL must be between 0 and 600 inclusive." );
        }
        this.ldl = ldl;
    }

    /**
     * Gets triglycerides level.
     *
     * @return the tri
     */
    public Integer getTri () {
        return tri;
    }

    /**
     * Sets triglycerides cholesterol. Between 100 and 600 inclusive.
     *
     * @param tri
     *            the tri to set
     */
    public void setTri ( final Integer tri ) {
        if ( tri == null ) {
            return;
        }
        if ( tri < 100 || tri > 600 ) {
            throw new IllegalArgumentException( "Triglycerides must be between 100 and 600 inclusive." );
        }
        this.tri = tri;
    }

    /**
     * Gets the smoking status of the patient's household.
     *
     * @return the houseSmokingStatus
     */
    public HouseholdSmokingStatus getHouseSmokingStatus () {
        return houseSmokingStatus;
    }

    /**
     * Sets the smoking status of the patient's household.
     *
     * @param houseSmokingStatus
     *            the houseSmokingStatus to set
     */
    public void setHouseSmokingStatus ( final HouseholdSmokingStatus houseSmokingStatus ) {
        this.houseSmokingStatus = houseSmokingStatus;
    }

    /**
     * Gets the smoking status of the patient.
     *
     * @return the patientSmokingStatus
     */
    public PatientSmokingStatus getPatientSmokingStatus () {
        return patientSmokingStatus;
    }

    /**
     * Sets the smoking status of the patient.
     *
     * @param patientSmokingStatus
     *            the patientSmokingStatus to set
     */
    public void setPatientSmokingStatus ( final PatientSmokingStatus patientSmokingStatus ) {
        this.patientSmokingStatus = patientSmokingStatus;
    }

}
