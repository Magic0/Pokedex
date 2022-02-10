import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PokeserviceService } from '../../services/pokeservice.service';
import { PokeInfo, PokeCadena, PokeStats, Poke } from '../../interfaces/poke.interface';
import { switchMap } from 'rxjs/operators';
@Component({
  selector: 'app-poke-tarjeta',
  templateUrl: './poke-tarjeta.component.html',
  styleUrls: ['./poke-tarjeta.component.scss']
})
export class PokeTarjetaComponent implements OnInit {
  
  pokeInfo:PokeInfo={};
  pokeCadena:PokeCadena[]=[];
  pokeStats:PokeStats={};
  pokemoves:string[]=[];
  pokedebilidades:string[]=[];
  constructor(private activatedRoute:ActivatedRoute,
              private router:Router,
              private pokeService:PokeserviceService) { }

  ngOnInit(): void {
    this.activatedRoute.params
    .pipe(
      switchMap(({name})=>this.pokeService.getPokemonesId(name))

    ).subscribe(
      pokeinfo=> {this.pokeInfo= pokeinfo
        this.pokeService.getDebilidades(this.pokeInfo.tipos!)
    .subscribe((data)=>{
      this.pokedebilidades=data
    })
                }
    ) 
    this.activatedRoute.params
    .pipe(
      switchMap(({name})=>this.pokeService.getPokemonCadena(name))

    ).subscribe(
      pokecadena=> {
        this.pokeCadena= pokecadena;
        console.log(pokecadena)
        }
    ) 
    this.activatedRoute.params
    .pipe(
      switchMap(({name})=>this.pokeService.getStats(name))

    ).subscribe(
      stats=> {
        this.pokeStats= stats;
        console.log(stats)
        }
    ) 
    this.activatedRoute.params
    .pipe(
      switchMap(({name})=>this.pokeService.getMoves(name))

    ).subscribe(
      moves=> {
        this.pokemoves= moves;
        console.log(moves)
        }
    ) 
    
  }

  
  
 

}
