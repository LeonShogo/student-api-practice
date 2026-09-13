import { Component, OnInit, signal} from '@angular/core';
import  { StudentModel } from './student.model';
import { StudentService } from './student.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-student',
  imports: [FormsModule],
  templateUrl: './student.html',
  styleUrl: './student.scss',
})
export class Student implements OnInit {

  students =signal<StudentModel[]>([]);
  newName = '';
  newAge: number | null = null;
  errorMessage = signal('');

  constructor(private studentService: StudentService) {}



  ngOnInit(): void {
    this.studentService.getAllStudents().subscribe(backendStudents => {
      this.students.set(backendStudents);
    });
  }


  addStudent(): void {
   if (!this.newName.trim() || this.newAge === null) {
      return;
    } 
    
    this.errorMessage.set('');

    this.studentService.createStudent({
      name: this.newName.trim(),
      age: this.newAge
    }).subscribe ({
      next: (savedStudent: StudentModel) => {
        this.students.update(students => [...students, savedStudent]);
        this.newName = '';
        this.newAge = null;
      },
      error: () => {
        this.errorMessage.set('Fehler beim Erstellen des Studenten. Bitte versuchen Sie es erneut.');
      }
    });
  }






}