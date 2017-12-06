package tarent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Thing {

	@Id
    @GeneratedValue
    private Long id;

	private String title;

 	@Column(name = "DETAILS")
	private String description;

    public Thing(final String title, final String description) {
        this.title = title;
        this.description = description;
    }

}