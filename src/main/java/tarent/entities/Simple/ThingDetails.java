package tarent.entities.Simple;

import javax.persistence.*;

@Embeddable
public class ThingDetails {

	private String title;

 	@Column(name = "DETAILS")
	private String description;

    public ThingDetails() {
    }

    public ThingDetails(final String title, final String description) {
        this.title = title;
        this.description = description;
    }

}