import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserClinicComponent } from './add-user-clinic.component';

describe('AddUserClinicComponent', () => {
  let component: AddUserClinicComponent;
  let fixture: ComponentFixture<AddUserClinicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddUserClinicComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddUserClinicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
