import { TestBed } from '@angular/core/testing';

import { MedicalRecordEntriesService } from './medical-record-entries.service';

describe('MedicalRecordEntriesService', () => {
  let service: MedicalRecordEntriesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalRecordEntriesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
