import { TestBed } from '@angular/core/testing';

import { ContabilidadService } from './contabilidad.service';

describe('ContabilidadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ContabilidadService = TestBed.get(ContabilidadService);
    expect(service).toBeTruthy();
  });
});
