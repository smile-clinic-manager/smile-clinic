import { TestBed } from '@angular/core/testing';

import { ApiIdFinderService } from './api-id-finder.service';

describe('ApiIdFinderService', () => {
  let service: ApiIdFinderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiIdFinderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
