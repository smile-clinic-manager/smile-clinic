import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestingBackComponent } from './testing-back.component';

describe('TestingBackComponent', () => {
  let component: TestingBackComponent;
  let fixture: ComponentFixture<TestingBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TestingBackComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestingBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
