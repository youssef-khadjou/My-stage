import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Stages } from './stages';

describe('Stages', () => {
  let component: Stages;
  let fixture: ComponentFixture<Stages>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Stages],
    }).compileComponents();

    fixture = TestBed.createComponent(Stages);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
