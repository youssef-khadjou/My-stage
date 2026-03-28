import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidationSujets } from './validation-sujets';

describe('ValidationSujets', () => {
  let component: ValidationSujets;
  let fixture: ComponentFixture<ValidationSujets>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ValidationSujets],
    }).compileComponents();

    fixture = TestBed.createComponent(ValidationSujets);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
