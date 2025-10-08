import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserClinicStepperComponent } from './add-user-clinic-stepper.component';

describe('AddUserClinicStepperComponent', () => {
  let component: AddUserClinicStepperComponent;
  let fixture: ComponentFixture<AddUserClinicStepperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddUserClinicStepperComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddUserClinicStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
