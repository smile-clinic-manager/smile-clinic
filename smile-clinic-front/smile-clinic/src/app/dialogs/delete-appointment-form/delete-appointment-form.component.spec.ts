import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAppointmentFormComponent } from './delete-appointment-form.component';

describe('DeleteAppointmentFormComponent', () => {
  let component: DeleteAppointmentFormComponent;
  let fixture: ComponentFixture<DeleteAppointmentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteAppointmentFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteAppointmentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
