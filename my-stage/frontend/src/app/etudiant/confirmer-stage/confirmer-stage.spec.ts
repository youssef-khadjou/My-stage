import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmerStage } from './confirmer-stage';

describe('ConfirmerStage', () => {
  let component: ConfirmerStage;
  let fixture: ComponentFixture<ConfirmerStage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirmerStage],
    }).compileComponents();

    fixture = TestBed.createComponent(ConfirmerStage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
