import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../../../category/services/category.service";
import {Category} from "../../../../../../types/types";

@Component({
  selector: 'app-category-management',
  templateUrl: './category-management.component.html',
  styleUrl: './category-management.component.css'
})
export class CategoryManagementComponent implements OnInit{

  categories: Category[] = [];

  constructor(
    private categoryService : CategoryService
  ) {}

  ngOnInit(): void {
    this.fetchAllCategories();
  }

  private fetchAllCategories(): void {
    this.categoryService.getAllCategories().subscribe({
      next: (data: Category[]) => {
        this.categories = data;
        console.log(data) // DEBUGGING
      },
      error: (err) => {
        console.error('Error fetching categories', err);
      },
      complete: () => {
        console.log('Category fetch complete');
      }
    });
  }

}
