import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Disponibilites } from './disponibilites';

describe('Disponibilites', () => {
  let component: Disponibilites;
  let fixture: ComponentFixture<Disponibilites>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Disponibilites],
    }).compileComponents();

    fixture = TestBed.createComponent(Disponibilites);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
