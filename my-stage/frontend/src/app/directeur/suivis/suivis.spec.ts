import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Suivis } from './suivis';

describe('Suivis', () => {
  let component: Suivis;
  let fixture: ComponentFixture<Suivis>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Suivis],
    }).compileComponents();

    fixture = TestBed.createComponent(Suivis);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
