import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Soutenances } from './soutenances';

describe('Soutenances', () => {
  let component: Soutenances;
  let fixture: ComponentFixture<Soutenances>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Soutenances],
    }).compileComponents();

    fixture = TestBed.createComponent(Soutenances);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
