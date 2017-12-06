package tarent.entities.Simple;

import javax.persistence.*;

@Entity
@Table(name = "THING")
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