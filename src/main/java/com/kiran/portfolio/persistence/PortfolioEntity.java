
package com.kiran.portfolio.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;


@Entity
@Table(name = "portfolios")
public class PortfolioEntity {

    @Id
    @Column(nullable = false, length = 50)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "base_currency", nullable = false, length = 3)
    private String baseCurrency;

    public PortfolioEntity() {
    }

    public PortfolioEntity(String id, String name, String baseCurrency) {
        this.id = id;
        this.name = name;
        this.baseCurrency = baseCurrency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}


/*
package defines the class's location in the Java package hierarchy.
import makes annotations from JPA available to this class.
@Entity tells JPA to manage this class as a persistent entity. Conceptually: "Hibernate, this class represents data that can be stored in the database."
@Table(name = "portfolios") explicitly names the database table.
@Id marks id as the primary key. Conceptually: "This field is the primary key for the entity."
The three fields represent the data we want to persist.
public PortfolioEntity() {} is the no-argument constructor. JPA requires a public or protected no-argument constructor for conventional entities.

Why keep both Portfolio and PortfolioEntity?
This is the architectural decision worth remembering:
- Portfolio represents your application's domain model.
- PortfolioEntity represents how the data is persisted.
- The adapter translates between the two.

*/