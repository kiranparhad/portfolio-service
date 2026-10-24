package com.kiran.portfolio.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioJpaRepository
        extends JpaRepository<PortfolioEntity, String> {

}
/*
PortfolioJpaRepository is an interface, not a class that you implement yourself.
Spring Data JPA generates the implementation at runtime.
Hibernate handles the ORM work, translating entity operations into SQL, and the PostgreSQL JDBC driver communicates with the database.

The two generic type parameters tell Spring Data JPA:
- PortfolioEntity — which entity this repository manages.
- String — the type of that entity's primary key.
By extending JpaRepository, you inherit methods such as:
Method	Purpose
save(entity)	Insert or update an entity
findById(id)	Retrieve an entity by its ID
findAll()	Retrieve all entities
deleteById(id)	Delete an entity by its ID
count()	Count the entities

*/