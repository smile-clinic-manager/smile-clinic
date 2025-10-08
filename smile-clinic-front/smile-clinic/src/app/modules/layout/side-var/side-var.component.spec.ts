import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideVarComponent } from './side-var.component';

describe('SideVarComponent', () => {
  let component: SideVarComponent;
  let fixture: ComponentFixture<SideVarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SideVarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SideVarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
