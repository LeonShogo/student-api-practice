package de.shogo.studentapi;

import de.shogo.studentapi.exception.StudentNotFoundException;
import de.shogo.studentapi.model.Student;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.http.MediaType;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import de.shogo.studentapi.controller.StudentController;
import de.shogo.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    void getStudent_whenNotFound_return404() throws Exception {

        when(studentService.getStudent(999L))
                .thenThrow(new StudentNotFoundException(999L));

        mockMvc.perform(get("/api/students/999"))
                .andExpect(status().isNotFound());

    }

    @Test
    void getStudent_whenFound_returnStudent() throws Exception {

        Student anna = new Student("Anna", 23);
        when(studentService.getStudent(1L)).thenReturn(Anna);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Anna"))
                .andExpect(jsonPath("$.age").value(23));

    }

    @Test
    void createStudent_whenInvalid_returns400() throws Exception {
    mockMvc.perform(post("/api/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {"name":"","age":-1}
                            """))
            .andExpect(status().isBadRequest());

    verifyNoInteractions(studentService);
}


@Test
void createStudent_whenValid_returns201() throws Exception {
    when(studentService.createStudent(any(Student.class)))
            .thenReturn(new Student("Max", 24));

    mockMvc.perform(post("/api/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {"name":"Max","age":24}
                            """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Max"))
            .andExpect(jsonPath("$.age").value(24));
}
    
}