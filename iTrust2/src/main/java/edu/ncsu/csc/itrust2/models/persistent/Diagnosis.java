package edu.ncsu.csc.itrust2.models.persistent;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.ncsu.csc.itrust2.forms.admin.DiagnosisForm;
import edu.ncsu.csc.itrust2.utils.DomainObjectCache;

/**
 * Diagnosis object, associated with a patient's office visit in the database
 *
 * @author jmphipps
 *
 */
@Entity
@Table ( name = "Diagnosis" )
public class Diagnosis extends DomainObject<Diagnosis> {

    /**
     * Cache of instances of Diagnoses
     */
    private static DomainObjectCache<String, Diagnosis> cache = new DomainObjectCache<String, Diagnosis>(
            Diagnosis.class );

    /**
     * Gets a list of all diagnoses in the database
     *
     * @return list of diagnoses
     */
    @SuppressWarnings ( "unchecked" )
    public static List<Diagnosis> getDiagnosis () {
        final List<Diagnosis> requests = (List<Diagnosis>) getAll( Diagnosis.class );
        requests.sort( new Comparator<Diagnosis>() {
            @Override
            public int compare ( final Diagnosis o1, final Diagnosis o2 ) {
                return o1.getId().compareTo( o2.getId() );
            }
        } );
        return requests;
    }

    /**
     * Finds a diagnosis with the given icd code in the database
     *
     * @param icd
     *            code of the diagnosis
     * @return the diagnosis
     */
    public static Diagnosis getByIcdCode ( final String icd ) {
        Diagnosis d = cache.get( icd );
        if ( null == d ) {
            try {
                d = getWhere( " icdCode = '" + icd + "'" ).get( 0 );
                cache.put( icd, d );
            }
            catch ( final Exception e ) {
                // Exception ignored
            }
        }
        return d;
    }

    /**
     * Empty constructor for hibernate
     */
    public Diagnosis () {
    }

    /**
     * Get list of diagnoses that satisfy the where statement
     *
     * @param where
     *            statement to find diagnoses by
     * @return list of diagnoses
     */
    @SuppressWarnings ( "unchecked" )
    private static List<Diagnosis> getWhere ( final String where ) {
        return (List<Diagnosis>) getWhere( Diagnosis.class, where );
    }

    /**
     * Converts info from an diagnosisform to diagnosis
     *
     * @param df
     *            form to get info from
     * @throws ParseException
     *             Error in parsing form.
     */
    public Diagnosis ( final DiagnosisForm df ) throws ParseException {
        setDiagnosisName( df.getDiagnosisName() );
        setIcdCode( df.getIcdCode() );
    }

    /**
     * ID of the diagnosis
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id;

    /**
     * Get ID of the diagnosis
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * Sets the id of the diagnosis
     *
     * @param id
     *            new id to set
     */
    @SuppressWarnings ( "unused" )
    private void setId ( final Long id ) {
        this.id = id;
    }

    /** Name of the diagnosis */
    @NotNull
    private String diagnosisName;
    /** ICD code of the diagnosis */
    @NotNull
    private String icdCode;

    /**
     * Gets the name of the diagnosis
     *
     * @return name of diagnosis
     */
    public String getDiagnosisName () {
        return diagnosisName;
    }

    /**
     * Sets the name of the diagnosis
     *
     * @param name
     *            to give the diagnosis
     */
    public void setDiagnosisName ( final String name ) {
        this.diagnosisName = name;
    }

    /**
     * Gets the icdCode of the diagnosis
     *
     * @return the icdCode
     */
    public String getIcdCode () {
        return icdCode;
    }

    /**
     * Sets the icdCode of the diagnosis
     *
     * @param code
     *            icdCode to give the diagnosis
     */
    public void setIcdCode ( final String code ) {
        /**
         * if ( code.equals( "None" ) ) { this.icdCode = code; return; } if (
         * !Pattern.matches( "^[A-Z]{1}[0-9]{2}$", String.valueOf( code ) ) ) {
         * throw new IllegalArgumentException( "ICD Code must start with a
         * single capital letter and be followed by 2 numbers" ); }
         */
        this.icdCode = code;
    }

    /**
     * Checks if two diagnoses are equal
     */
    @Override
    public boolean equals ( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Diagnosis other = (Diagnosis) obj;
        if ( !diagnosisName.equals( other.diagnosisName ) ) {
            return false;
        }
        if ( !icdCode.equals( other.icdCode ) ) {
            return false;
        }
        /**
         * if ( id == null ) { if ( other.id != null ) { return false; } } else
         * if ( !id.equals( other.id ) ) { return false; }
         */

        return true;
    }

}
