import { Component, OnInit } from '@angular/core';
import { PokeserviceService } from '../../services/pokeservice.service';
import { Poke, PokeInfo } from '../../interfaces/poke.interface';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lista-poke',
  templateUrl: './lista-poke.component.html',
  styleUrls: ['./lista-poke.component.scss']
})
export class ListaPokeComponent implements OnInit {

  constructor(private pokeservice:PokeserviceService,
              private router:Router) { }
  
   pokemones:Poke[]=[]
   offset:number=0;
   arregloTipos:string[]=[];
   

  ngOnInit(): void {
    this.pokeservice.getPokemones(this.offset)
    .subscribe((data)=> {this.pokemones=data;
      console.log(data)
    
    })
    
    
  }
  viajar(name:string){
      this.router.navigateByUrl(`/${name}`)
  }
  aumentarPokemon(){
    this.offset+=20
    this.pokeservice.getPokemones(this.offset)
    .subscribe( data=> {this.pokemones = this.pokemones.concat(data)})
  }
  agregarTipo(tipo:string){
  console.log(tipo)
  
  if(this.arregloTipos.length <2&& !this.arregloTipos.includes(tipo)){
    this.arregloTipos.push(tipo);
    console.log(this.arregloTipos)
  }else if(!this.arregloTipos.includes(tipo)){
    this.arregloTipos.shift()
    this.arregloTipos.push(tipo)
  }
  this.pokeservice.getTipo(this.arregloTipos)
  .subscribe( data=>{
    console.log(data)
    this.pokemones= data

  })
    
  }

}
