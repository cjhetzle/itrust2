package edu.ncsu.csc.itrust2.models.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import edu.ncsu.csc.itrust2.forms.admin.DrugForm;
import edu.ncsu.csc.itrust2.utils.DomainObjectCache;

/**
 * Creates a drug object
 *
 * @author Nicholas Luther
 */
@Entity
@Table ( name = "Drugs" )
public class Drug extends DomainObject<Drug> implements Serializable {

    /**
     * Used for serializing the object.
     */
    private static final long                      serialVersionUID = 1L;

    /**
     * In-memory cache that will store instances of the Hospital to avoid
     * retrieval trips to the database.
     */
    static private DomainObjectCache<String, Drug> cache            = new DomainObjectCache<String, Drug>( Drug.class );

    /**
     * The name of the drug
     */
    @NotEmpty
    @Length ( max = 255 )
    private String                                 name;

    /**
     * The national drug code
     */
    @NotEmpty
    @Pattern ( regexp = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9]" )
    @Id
    private String                                 ndc;

    /**
     * Empty constructor for AngularJS
     */
    public Drug () {
    }

    /**
     * Constructor from front end form
     *
     * @param df
     *            the front end drug form
     */
    public Drug ( DrugForm df ) {
        name = df.getName();
        ndc = df.getNdc();
    }

    /**
     * Retrieve all Drugs from the database
     *
     * @return Drugs found
     */
    @SuppressWarnings ( "unchecked" )
    public static List<Drug> getDrugs () {
        return (List<Drug>) getAll( Drug.class );
    }

    /**
     * Retrieve all matching Drugs from the database that match a where clause
     * provided.
     *
     * @param where
     *            Clause to match by
     * @return The matching Drugs
     */
    @SuppressWarnings ( "unchecked" )
    private static List<Drug> getWhere ( final String where ) {
        return (List<Drug>) getWhere( Drug.class, where );
    }

    /**
     * Retrieve a Drug by its NDC.
     *
     * @param id
     *            The ID of the Drug
     * @return The Drug, if found, or null if not found.
     */
    public static Drug getById ( final String id ) {
        Drug request = cache.get( id );
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
     * Retrieve the name of the drug
     *
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * Retrieve the national drug code
     *
     * @return the national drug code
     */
    public String getNdc () {
        return ndc;
    }

    /**
     * Retrieve the id (ndc)
     *
     * @return the national drug code
     */
    @Override
    public String getId () {
        return ndc;
    }

}
