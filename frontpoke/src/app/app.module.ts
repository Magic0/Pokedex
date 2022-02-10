import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ListaPokeComponent } from './components/lista-poke/lista-poke.component';
import { PokeTarjetaComponent } from './components/poke-tarjeta/poke-tarjeta.component';
@NgModule({
  declarations: [
    AppComponent,
    ListaPokeComponent,
    PokeTarjetaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
