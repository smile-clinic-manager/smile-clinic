import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeethDetailComponent } from './teeth-detail.component';

describe('TeethDetailComponent', () => {
  let component: TeethDetailComponent;
  let fixture: ComponentFixture<TeethDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeethDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeethDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
