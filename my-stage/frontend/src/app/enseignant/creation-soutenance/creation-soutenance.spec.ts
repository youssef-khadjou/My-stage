import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreationSoutenance } from './creation-soutenance';

describe('CreationSoutenance', () => {
  let component: CreationSoutenance;
  let fixture: ComponentFixture<CreationSoutenance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreationSoutenance],
    }).compileComponents();

    fixture = TestBed.createComponent(CreationSoutenance);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
