import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalRecordEntryFormComponent } from './medical-record-entry-form.component';

describe('MedicalRecordEntryFormComponent', () => {
  let component: MedicalRecordEntryFormComponent;
  let fixture: ComponentFixture<MedicalRecordEntryFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicalRecordEntryFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicalRecordEntryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
