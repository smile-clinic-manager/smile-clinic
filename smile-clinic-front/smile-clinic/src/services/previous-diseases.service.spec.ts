import { TestBed } from '@angular/core/testing';

import { PreviousDiseasesService } from './previous-diseases.service';

describe('PreviousDiseasesService', () => {
  let service: PreviousDiseasesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreviousDiseasesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
