package com.unideb;

import com.unideb.error.StudentNotFoundException;
import com.unideb.error.StudentUnSupportedFieldPatchException;
import com.unideb.error.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    // Teszt
    @CrossOrigin(origins = "*") // CORS hibák feloldása céljából
    @GetMapping("/test")
    String greet() {
        return "Hello!";
    }

    // C.R.U.D. műveletek:



    // -----------CREATE-----------

    // Új hallgató felvétele  >>> body
    @CrossOrigin(origins = "*")
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    Student newBook(@RequestBody Student newBook) {
        return repository.save(newBook);
    }



    // -----------READ-----------

    // Összes hallgató kiíratása
    @CrossOrigin(origins = "*")
    @GetMapping("/students")
    List<Student> findAll() {
        return repository.findAll();
    }

    // Külföldi vagy magyar hallgatók kiíratása
    @CrossOrigin(origins = "*")
    @GetMapping("/foreign")
    List<Student> getIsForeignStudent(@RequestParam boolean status) {
        return repository.findAll().stream().filter(x -> x.getForeignStudent() == status).collect(Collectors.toList());
    }

    // Fiatalabb, mint
    @CrossOrigin(origins = "*")
    @GetMapping("/youngerThan")
    List<Student> getYoungerThan(@RequestParam int age) {
        return repository.findAll().stream().filter(x -> x.getAge() < age).collect(Collectors.toList());
    }

    // Idősebb, mint
    @CrossOrigin(origins = "*")
    @GetMapping("/olderThan")
    List<Student> getOlderThan(@RequestParam int age) {
        return repository.findAll().stream().filter(x -> x.getAge() > age).collect(Collectors.toList());
    }

    // Hallgató keresése ID alapján
    @CrossOrigin(origins = "*")
    @GetMapping("/students/{id}")
    Student findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    // Hallgató keresése keresztnév alapján
    @CrossOrigin(origins = "*")
    @GetMapping("/firstname")
    List<Student> findFName(@RequestParam String fname) {
        return repository.findAll().stream().filter(x -> x.getFirstName().equals(fname)).collect(Collectors.toList());
    }

    // Hallgató keresése vezetéknév alapján
    @CrossOrigin(origins = "*")
    @GetMapping("/lastname")
    List<Student> findLName(@RequestParam String lname) {
        return repository.findAll().stream().filter(x -> x.getLastName().equals(lname)).collect(Collectors.toList());
    }

    // Hallgató keresése kar alapján
    @CrossOrigin(origins = "*")
    @GetMapping("/faculty")
    List<Student> findFaculty(@RequestParam String fac) {
        return repository.findAll().stream().filter(x -> x.getFaculty().equals(fac)).collect(Collectors.toList());
    }

    // Hallgató keresése egyetem alapján
    @CrossOrigin(origins = "*")
    @GetMapping("/university")
    List<Student> findUniversity(@RequestParam String uni) {
        return repository.findAll().stream().filter(x -> x.getUniversity().equals(uni)).collect(Collectors.toList());
    }



    // -----------UPDATE-----------

    // Új hallgató felvétele / meglévő frissítése ID alapján  >>> body
    @CrossOrigin(origins = "*")
    @PutMapping("/students/{id}")
    Student saveOrUpdate(@RequestBody Student newStudent, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setFirstName(newStudent.getFirstName());
                    x.setLastName((newStudent.getLastName()));
                    x.setAge(newStudent.getAge());
                    x.setUniversity(newStudent.getUniversity());
                    x.setFaculty(newStudent.getFaculty());
                    x.setForeignStudent(newStudent.getForeignStudent());

                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
    }

    // Csakis a hallgató egyetemének frissítése ID alapján  >>> body
    @CrossOrigin(origins = "*")
    @PatchMapping("/student_uni/{id}")
    Student patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String university = update.get("university");
                    if (!StringUtils.isEmpty(university)) {
                        x.setUniversity(university);

                        return repository.save(x);
                    } else {
                        throw new StudentUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new StudentNotFoundException(id);
                });

    }

    // Csakis a hallgató karának frissítése ID alapján  >>> body
    @CrossOrigin(origins = "*")
    @PatchMapping("/student_fac/{id}")
    Student patch2(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String faculty = update.get("faculty");
                    if (!StringUtils.isEmpty(faculty)) {
                        x.setFaculty(faculty);

                        return repository.save(x);
                    } else {
                        throw new StudentUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new StudentNotFoundException(id);
                });

    }



    // -----------DELETE-----------

    // Hallgató törlése ID alapján
    @CrossOrigin(origins = "*")
    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }


    // Összes hallgató törlése
    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteallstudents")
    void deleteAllStudents() {
        List<Student> students = repository.findAll();
        for (Student student : students) {
            repository.deleteById(student.getId());
        }
    }
}