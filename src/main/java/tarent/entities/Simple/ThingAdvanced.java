package tarent.entities.Simple;

import javax.persistence.*;

@Entity
public class ThingAdvanced {

	@Id
    @GeneratedValue
    private Long id;

 	@Embedded
    private ThingDetails details;

    public ThingAdvanced(final ThingDetails embeddedDetails) {
        details = embeddedDetails;
    }

}