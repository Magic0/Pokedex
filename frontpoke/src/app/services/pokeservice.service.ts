import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class PokeserviceService {

  constructor(private http:HttpClient) { }

  getPokemones(data:number):Observable<any>{
    let params = new HttpParams();
    params = params.append('offset',data)
    return this.http.get<any>('http://localhost:8080/pokedex',{params})
  }
  getPokemonesId(id:string):Observable<any>{
    let params = new HttpParams();
    params = params.append('name',id)
    return this.http.get<any>('http://localhost:8080/pokedex/pokemon',{params})
  }
  getPokemonCadena(id:string):Observable<any>{
    let params = new HttpParams();
    params = params.append('name',id)
    return this.http.get<any>('http://localhost:8080/pokedex/evolution',{params})
  }
  getTipo(arreglo:string[]){
    let params = new HttpParams();
    params = params.append('tipo',arreglo[0].toLocaleLowerCase())
    if(arreglo[1]){
      params = params.append('tipo',arreglo[1].toLocaleLowerCase())

    }
    return this.http.get<any>('http://localhost:8080/pokedex/filtro',{params})

  }
  getStats(id:string):Observable<any>{
    let params = new HttpParams();
    params = params.append('name',id)
    return this.http.get<any>('http://localhost:8080/pokedex/stats',{params})
  }
  getMoves(id:string):Observable<any>{
    let params = new HttpParams();
    params = params.append('name',id)
    return this.http.get<any>('http://localhost:8080/pokedex/movimientos',{params})
  }
  getDebilidades(arreglo:string[]):Observable<any>{
    let params = new HttpParams();
    params = params.append('tipo',arreglo[0].toLocaleLowerCase())
    if(arreglo[1]){
      params = params.append('tipo',arreglo[1].toLocaleLowerCase())

    }
    return this.http.get<any>('http://localhost:8080/pokedex/debil',{params})
  }
  


}
