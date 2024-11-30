import {Component} from '@angular/core';
import {StorageService} from "../../authentication/storage.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {

  currentYear: number = new Date().getFullYear();

  constructor(
    protected storageService : StorageService,
  ) {}

}
