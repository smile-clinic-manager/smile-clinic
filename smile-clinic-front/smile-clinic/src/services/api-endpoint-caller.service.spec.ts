import { TestBed } from '@angular/core/testing';

import { ApiEndpointCallerService } from './api-endpoint-caller.service';

describe('ApiEndpointCallerService', () => {
  let service: ApiEndpointCallerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiEndpointCallerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
