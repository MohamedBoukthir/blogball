import {Component, OnInit, ViewChild} from '@angular/core';
import {CategoryService} from "../../../../category/services/category.service";
import {Category} from "../../../../../../types/types";

@Component({
  selector: 'app-category-management',
  templateUrl: './category-management.component.html',
  styleUrl: './category-management.component.css'
})
export class CategoryManagementComponent implements OnInit{

  categories: Category[] = [];
  newCategory: Category = {id: 0, name:''};

  // Access the modal dialog element
  @ViewChild('myModal') myModal: any;
  @ViewChild('myModal2') myModal2: any;

  openAddCategoryModal() {
    this.myModal.nativeElement.showModal(); // Show modal using the showModal method
  }

  closeAddCategoryModal() {
    this.myModal.nativeElement.close(); // Close modal using the close method
  }


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

  addNewCategory() {
    if (this.newCategory.name.trim()) {
      this.categoryService.addCategory(this.newCategory).subscribe({
        next: (category : Category) => {
          this.categories.push(category);
          this.closeAddCategoryModal();
          this.newCategory = {id: 0, name: ''};
        },
        error: (err) => {
         console.error('Error adding categories', err);
        },
        complete:() => {
          console.log('Category added.')
        }
      })
    }
   }

  deleteCategory(categoryId: number) {
    console.log('Deleting category with ID:', categoryId);
    this.categoryService.deleteCategory(categoryId).subscribe({
      next: () => {
        this.fetchAllCategories();  // Refresh the categories list
      },
      error:(error) => {
      console.error("Error deleting category:", error)
      }
    });
  }

}
