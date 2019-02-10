package malikin.github.io.blitz.statistics.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min=1, message="Name should have at least 1 character")
    private String name;

    @NotNull
    private Long externalUid;

    private Long count = 1L;

    public Event() {}

    public Event(
            @NotNull @Size(min = 1, message = "Name should have at least 1 character") String name,
            @NotNull Long externalUid
    ) {
        this.name = name;
        this.externalUid = externalUid;
    }

    public void setExternalUid(Long externalUid) {
        this.externalUid = externalUid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getExternalUid() {
        return externalUid;
    }

    public Long getCount() {
        return count;
    }
}
