package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EntityA {

    @Id
    private Integer id;

    @JoinColumn(name = "a_id")
    @OneToMany
    private List<EntityB> bCollection = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<EntityB> getbCollection() {
        return bCollection;
    }

}
