import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserClinicRolesComponent } from './edit-user-clinic-roles.component';

describe('EditUserClinicRolesComponent', () => {
  let component: EditUserClinicRolesComponent;
  let fixture: ComponentFixture<EditUserClinicRolesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditUserClinicRolesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserClinicRolesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
