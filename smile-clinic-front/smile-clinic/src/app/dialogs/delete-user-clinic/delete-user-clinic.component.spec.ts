import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteUserClinicComponent } from './delete-user-clinic.component';

describe('DeleteUserClinicComponent', () => {
  let component: DeleteUserClinicComponent;
  let fixture: ComponentFixture<DeleteUserClinicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteUserClinicComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteUserClinicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
