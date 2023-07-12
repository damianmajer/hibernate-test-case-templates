package org.hibernate.bugs;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EntityB {

    @Id
    private Integer id;

    @JoinColumn(name = "a_id")
    @ManyToOne
    private EntityA a;

    @ElementCollection
    @CollectionTable(name = "entity_c", joinColumns = {@JoinColumn(name = "id")})
    private final Set<EntityC> cCollection = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntityA getA() {
        return a;
    }

    public void setA(EntityA a) {
        this.a = a;
    }

    public Set<EntityC> getcCollection() {
        return cCollection;
    }
}
