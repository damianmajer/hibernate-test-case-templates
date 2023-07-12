package org.hibernate.bugs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
