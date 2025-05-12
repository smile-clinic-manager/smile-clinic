import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalRecordEntryListComponent } from './medical-record-entry-list.component';

describe('MedicalRecordEntryListComponent', () => {
  let component: MedicalRecordEntryListComponent;
  let fixture: ComponentFixture<MedicalRecordEntryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicalRecordEntryListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicalRecordEntryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
