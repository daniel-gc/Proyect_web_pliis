import { TestBed } from '@angular/core/testing';

import { ApplicationInitService } from './application-init.service';

describe('ApplicationInitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ApplicationInitService = TestBed.get(ApplicationInitService);
    expect(service).toBeTruthy();
  });
});
