import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PokeTarjetaComponent } from './components/poke-tarjeta/poke-tarjeta.component';
import { ListaPokeComponent } from './components/lista-poke/lista-poke.component';

const routes: Routes = [
  {
    path:'listado',
    component:ListaPokeComponent
  },
  {
    path:':name',
    component: PokeTarjetaComponent
  },
  {
    path:'**',
    redirectTo:'listado'

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
