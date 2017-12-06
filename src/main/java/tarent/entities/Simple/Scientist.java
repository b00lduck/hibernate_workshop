package tarent.entities.Simple;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Scientist {

	@Id
    @GeneratedValue
    private Long id;

	private String name;

	@ElementCollection
    @CollectionTable(name = "IDEAS")
    private Set<String> ideas = new HashSet<>();

    public Collection<String> getIdeas() {
        return ideas;
    }

    public Scientist(final String name) {
        this.name = name;
    }
}