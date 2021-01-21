import { TestBed } from '@angular/core/testing';

import { ImportaDatosService } from './importa-datos.service';

describe('ImportaDatosServiceService', () => {
  let service: ImportaDatosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImportaDatosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
