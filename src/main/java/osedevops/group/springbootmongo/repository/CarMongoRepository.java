package osedevops.group.springbootmongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import osedevops.group.springbootmongo.model.Car;

@NoRepositoryBean

public interface CarMongoRepository extends CrudRepository{}