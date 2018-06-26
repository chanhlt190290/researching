package com.chanhlt.memcached.entiry;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = -2179934342484313105L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private double score;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinTable(name = "person_in_charge",
    //         inverseJoinColumns = {
    //             @JoinColumn(name = "contract_id", referencedColumnName = "id")},
    //         joinColumns = {
    //             @JoinColumn(name = "user_id", referencedColumnName = "id")})
    // private Set<Contract> contracts = new HashSet<>();

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public double getScore() {
        return score;
    }

    /**
     * @param type the type to set
     */
    public void setScore(double type) {
        this.score = type;
    }

    // /**
    //  * @return the contracts
    //  */
    // public Set<Contract> getContracts() {
    //     return contracts;
    // }

    // /**
    //  * @param contracts the contracts to set
    //  */
    // public void setContracts(Set<Contract> contracts) {
    //     this.contracts = contracts;
    // }
}