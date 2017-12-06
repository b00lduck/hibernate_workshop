package tarent.entities.Simple;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Scientist {

	@Id
    @GeneratedValue
    private Long id;

	private String name;

	@ElementCollection
    @CollectionTable(name = "IDEAS")
    private Collection<String> ideas = new ArrayList<>();

    public Collection<String> getIdeas() {
        return ideas;
    }

    public Scientist(final String name) {
        this.name = name;
    }
}