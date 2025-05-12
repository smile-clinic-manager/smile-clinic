import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalRecordEntryDetailComponent } from './medical-record-entry-detail.component';

describe('MedicalRecordEntryDetailComponent', () => {
  let component: MedicalRecordEntryDetailComponent;
  let fixture: ComponentFixture<MedicalRecordEntryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicalRecordEntryDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicalRecordEntryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
