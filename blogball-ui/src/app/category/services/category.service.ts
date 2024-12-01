import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../../../../types/types";

const BASE_URL = 'http://localhost:8080/api/v1/categories';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${BASE_URL}/all-categories`, httpOptions);
  }

  getCategory(id: number):Observable<Category> {
    return this.http.get<Category>(`${BASE_URL}/category/` + id, httpOptions);
  }

  addCategory(category: Category):Observable<Category> {
    return this.http.post<Category>(`${BASE_URL}/add-category`, category, httpOptions);
  }

  updateCategory(id: number):Observable<Category> {
    return this.http.put<Category>(`${BASE_URL}/update-category/` + id , httpOptions);
  }

  deleteCategory(id: number):Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/delete-category/` + id , httpOptions);
  }

}
