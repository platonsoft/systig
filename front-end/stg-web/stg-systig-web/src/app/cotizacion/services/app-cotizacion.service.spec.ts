import { TestBed } from '@angular/core/testing';

import { AppCotizacionService } from './app-cotizacion.service';

describe('AppCotizacionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppCotizacionService = TestBed.get(AppCotizacionService);
    expect(service).toBeTruthy();
  });
});
