import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CateoryNavbarComponent } from './cateory-navbar.component';

describe('CateoryNavbarComponent', () => {
  let component: CateoryNavbarComponent;
  let fixture: ComponentFixture<CateoryNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CateoryNavbarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CateoryNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
