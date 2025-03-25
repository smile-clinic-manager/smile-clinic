import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserClinicComponent } from './update-user-clinic.component';

describe('UpdateUserClinicComponent', () => {
  let component: UpdateUserClinicComponent;
  let fixture: ComponentFixture<UpdateUserClinicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateUserClinicComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateUserClinicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
