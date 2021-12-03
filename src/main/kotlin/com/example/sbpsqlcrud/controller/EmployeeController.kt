package com.example.sbpsqlcrud.controller

import com.example.sbpsqlcrud.exception.ResourceNotFoundException
import com.example.sbpsqlcrud.model.Employee
import com.example.sbpsqlcrud.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/employees")
class EmployeeController (@Autowired private val employeeRepository: EmployeeRepository) {

    @GetMapping
    fun getEmployees() = employeeRepository.findAll()

    @GetMapping("/{id}")
    @Throws(ResourceNotFoundException::class)
    fun getEmployee(@PathVariable id: Long): ResponseEntity<Employee> {
        val employee = employeeRepository
            .findById(id)
            .orElseThrow {
                ResourceNotFoundException("Employee with id: $id is not found")
            }
        return ResponseEntity(employee,HttpStatus.OK)
    }

    @PostMapping
    fun postEmployee(@RequestBody employee: Employee) = employeeRepository.save(employee)

    @PutMapping("/{id}")
    fun putEmployee(@PathVariable id: Long,
                    @RequestBody employee: Employee
    ): ResponseEntity<Employee> =
        if (employeeRepository.existsById(id))
            ResponseEntity(employeeRepository.save(employee), HttpStatus.OK)
        else
            ResponseEntity(employeeRepository.save(employee), HttpStatus.CREATED)

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long) =
        employeeRepository.deleteById(id)
}
