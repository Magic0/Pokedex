import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaPokeComponent } from './lista-poke.component';

describe('ListaPokeComponent', () => {
  let component: ListaPokeComponent;
  let fixture: ComponentFixture<ListaPokeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaPokeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaPokeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
