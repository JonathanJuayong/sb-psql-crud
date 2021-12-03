package com.example.sbpsqlcrud.repository

import com.example.sbpsqlcrud.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long>