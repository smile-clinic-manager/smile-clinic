import { TestBed } from '@angular/core/testing';

import { ApiEndpointHelperService } from './api-endpoint-helper.service';

describe('ApiEndpointHelperService', () => {
  let service: ApiEndpointHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiEndpointHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
