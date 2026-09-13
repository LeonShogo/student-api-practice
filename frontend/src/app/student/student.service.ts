import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudentModel } from './student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiUrl = 'http://localhost:8080/api/students';

  constructor(private http: HttpClient) {}

  getStudent(id: number): Observable<StudentModel> {
    return this.http.get<StudentModel>(`${this.apiUrl}/${id}`);
  }

  getAllStudents(): Observable<StudentModel[]> {
    return this.http.get<StudentModel[]>(this.apiUrl);
  }

  createStudent(student: Omit<StudentModel, 'id'>): Observable<StudentModel> {
    return this.http.post<StudentModel>(this.apiUrl, student);
  }

  


}
